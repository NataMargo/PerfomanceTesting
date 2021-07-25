package demo

import io.gatling.core.Predef.{css, _}
import io.gatling.http.Predef._


class FirstProject1 extends Simulation {
  val environment = System.getProperty("apiUrl")
  val ramp_users = Integer.getInteger("ramp_users", 5)
  val ramp_duration = Integer.getInteger("ramp_duration")
  val duration = Integer.getInteger("duration")

  val httpProtocol = http
    .baseUrl("https://challenge.flood.io")

    .inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("uk-UA,uk;q=0.8,en-US;q=0.5,en;q=0.3")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:89.0) Gecko/20100101 Firefox/89.0")
    .disableFollowRedirect

  val headers_0 = Map("Upgrade-Insecure-Requests" -> "1")

  val headers_1 = Map(
    "Origin" -> "https://challenge.flood.io",
    "Upgrade-Insecure-Requests" -> "1")

  val headers_5 = Map(
    "Accept" -> "*/*",
    "X-Requested-With" -> "XMLHttpRequest")
  val think_time_min = 1
  val think_time_max = 2


  val scn = scenario("FirstProject1")
    // home
    .exec(http("Open Home Page")
      .get("/")
      .check(regex("""<title>(.+?)</title>""").is("Flood IO Script Challenge"),
        regex("""<title>(.+?)</title>""").saveAs("title"),
        css("input[name='authenticity_token']", "value").find.saveAs("token")
      )
      .headers(headers_0))
    .exec(
      session => {
        println("INFO")
        println(session("title").as[String])
        println(session("token").as[String])

        session
      }

    )
    .pause(think_time_min, think_time_max)
    // start
    .exec(http("Press Start Button")
      .post("/start")
      .headers(headers_1)
      .formParam("utf8", "✓")

      .check(css("input[name='challenger[step_id]']", "value").find.saveAs("stepId"))

      .formParam("authenticity_token", "${token}")
      .formParam("challenger[step_id]", "${stepId}")
      .formParam("challenger[step_number]", "1")
      .formParam("commit", "Start"))
    .exec(
      session => {
        println("INFO2")
        println(session("stepId").as[String])
        println(session("token").as[String])

        session
      })
    .pause(think_time_min, think_time_max)
    // age
    .exec(http("Enter your age")
      .post("/start")
      .headers(headers_1)
      .check(
        xpath("//label[@class='collection_radio_buttons']").findAll.saveAs("OrderValue"),
        xpath("//input[@class='radio_buttons optional']").findAll.saveAs("OrderSelected"))
      .formParam("utf8", "✓")
      .formParam("authenticity_token", "${token}")
      .formParam("challenger[step_id]", "${stepId}")
      .formParam("challenger[step_number]", "2")
      .formParam("challenger[age]", "22")
      .formParam("commit", "Next")

    )
    .exec(
      session => {
        val OrderValues = session("OrderValue").as[List[Int]]
        val maxValue = OrderValues.max
        println("INFO3")
        println(session("OrderValue").as[String])
        println(session("maxValue").as[String])

        session
      })
    .pause(think_time_min, think_time_max)


    // max order value
    .exec(http("Enter Max Order Value")
      .get("/start")
      .headers(headers_1)
      .formParam("utf8", "✓")
      .check(css("input[name='challenger[step_id]']", "value").saveAs("stepId"))
      .formParam("authenticity_token", "${token}")
      .formParam("challenger[step_id]", "${stepId}")
      .formParam("challenger[step_number]", "3")
      .formParam("challenger[largest_order]", "218")
      .formParam("challenger[order_selected]", "TnZjbElWcFdGR0VRZWdSbGx4K1Z4dz09LS16bk5jTjlJc1MxT3I5d2pySDVLYzFBPT0=--fe0a72704a48ff8a51e533ab19e5f2a827175eef")
      .formParam("commit", "Next"))
    .pause(think_time_min, think_time_max)
    // step4 shown
    // next button
    .exec(http("Click Next Button")
      .get("/start")
      .headers(headers_1)
      .formParam("utf8", "✓")
      .formParam("authenticity_token", "veVZ6d4k6UNXlYbJUE9dnC2iSjJcLkm7Yd8o5TIER+4=")
      .formParam("challenger[step_id]", "YzNPdndBOXdZMTV1QkdsRTJwLzZqQT09LS01YXFSb3djc1NEbWhpTmJ4QXhpWUJ3PT0=--ddd61adc8d72aacb4a3c2ba3725aeadec2a66b52")
      .formParam("challenger[step_number]", "4")
      .formParam("challenger[order_6]", "1626174137")
      .formParam("challenger[order_10]", "1626174137")
      .formParam("challenger[order_3]", "1626174137")
      .formParam("challenger[order_6]", "1626174137")
      .formParam("challenger[order_9]", "1626174137")
      .formParam("challenger[order_9]", "1626174137")
      .formParam("challenger[order_12]", "1626174137")
      .formParam("challenger[order_9]", "1626174137")
      .formParam("challenger[order_13]", "1626174137")
      .formParam("challenger[order_13]", "1626174137")
      .formParam("commit", "Next")
      .resources(http("Get Code Page")
        .get("/code")
        .check(status.is(200))
        .headers(headers_5)))
    .pause(think_time_min, think_time_max)
    // token Enter
    .exec(http("Enter Code")
      .get("/start")
      .headers(headers_1)
      .formParam("utf8", "✓")
      .formParam("authenticity_token", "veVZ6d4k6UNXlYbJUE9dnC2iSjJcLkm7Yd8o5TIER+4=")
      .formParam("challenger[step_id]", "QjBIb3gvdUFjM1hnNVBQWnMvdkl3Zz09LS1rRjRuOVFQbUU1OGtJSjRvZmFDbTJRPT0=--08ac012bcdbcf674f209ff0e2d91abb8f29abb64")
      .formParam("challenger[step_number]", "5")
      .formParam("challenger[one_time_token]", "2691358042")
      .formParam("commit", "Next"))
    .pause(think_time_min, think_time_max)
  // done page



  setUp(
    scn.inject(atOnceUsers(1)),
    // scnTwo.inject(rampUsers(ramp_users) during ramp_duration)
  )
    .assertions(global.successfulRequests.percent.gt(95))
    .assertions((global.responseTime.max.lt(5000)))
    .protocols(httpProtocol)

}