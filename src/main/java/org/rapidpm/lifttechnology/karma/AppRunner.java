/**
 * Copyright © 2017 Sven Ruppert (sven.ruppert@gmail.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.rapidpm.lifttechnology.karma;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.javalin.Javalin;
import org.rapidpm.frp.model.Pair;

import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Function;

import static java.time.LocalDateTime.now;
import static java.util.stream.Collectors.toList;
import static java.util.stream.LongStream.rangeClosed;
import static org.rapidpm.frp.matcher.Case.match;
import static org.rapidpm.frp.matcher.Case.matchCase;
import static org.rapidpm.frp.model.Result.failure;
import static org.rapidpm.frp.model.Result.success;
import static org.rapidpm.lifttechnology.karma.ElevatorStatus.*;

public class AppRunner {
  private AppRunner() {
  }


  public static void main(String[] args) {


    createRandomData();


    final Javalin app = Javalin.create();

    app.enableRouteOverview("/info");

    app.get("/events/:elevator-id", ctx -> {

      String elevatorID = ctx.pathParam("elevator-id");
      ctx.result(event2json().apply(id2Events.getOrDefault(Long.valueOf(elevatorID), Collections.emptyList())));
    });


    app.get("/demo", ctx -> ctx.result("more data is comming soon"));


    app.get("/overview", ctx -> ctx.result(info2json().apply(elevatorList)));

    app.start(7000);


    app.get("/", ctx -> ctx.result("Hello World"));
  }

  private static Map<Long, List<Event>> id2Events = new HashMap<>();

  private static List<ElevatorInfo> elevatorList = new ArrayList<>();


  private static Function<Integer, ElevatorStatus> toStatus() {
    return (i) -> match(matchCase(() -> failure("no status")), matchCase(() -> i == 0, () -> success(GREEN)),
                        matchCase(() -> i == 1, () -> success(YELLOW)), matchCase(() -> i == 2, () -> success(RED))
                       ).get();
  }

  private static void createRandomData() {

    Random rnd = new Random();
    elevatorList = rangeClosed(1, 10)
        .mapToObj(id -> new ElevatorInfo(id, "Lift Nr " + id, toStatus().apply(rnd.nextInt(3)), now()))
        .collect(toList());


    List<Event> eventList = elevatorList
        .stream()
        .map(e -> new Pair<>(e, rangeClosed(1, 10)
            .mapToObj(
                eventID -> new Event(e.getElevatorID(), toStatus().apply(rnd.nextInt(3)), now().minusHours(eventID),
                                     "Message " + eventID
                ))
            .collect(toList())))
        .flatMap(p -> {
          final Event first = new Event(p.getT1().getElevatorID(), p.getT1().getElevatorStatus(),
                                        p.getT1().getTimestamp(), "A nice message"
          );
          List<Event> result = new ArrayList<>();
          result.add(first);
          result.addAll(p.getT2());
          return result.stream();
        })
        .collect(toList());
    id2Events.put(eventList.get(0).getElevatorID(), eventList);


  }


  private static final Type INFO_LIST_TYPE = new TypeToken<List<ElevatorInfo>>() { }.getType();

  public static Function<List<ElevatorInfo>, String> info2json() {
    return (e) -> new GsonBuilder().create().toJson(e, INFO_LIST_TYPE);
  }


  private static final Type EVENT_LIST_TYPE = new TypeToken<List<Event>>() { }.getType();

  public static Function<List<Event>, String> event2json() {
    return (e) -> new GsonBuilder().create().toJson(e, EVENT_LIST_TYPE);
  }


}
