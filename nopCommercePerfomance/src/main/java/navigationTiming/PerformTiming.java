package navigationTiming;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.openqa.selenium.JavascriptExecutor;
import utils.WebDriverHolder;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static utils.Constants.*;

public class PerformTiming {
    Map<String, Object> timings = null;
    private final String javaScriptForPerformance = "var timings =performance.timing || {};return timings;";

    InfluxDB influxDB = InfluxDBFactory.connect(DATABASE_URL, USERNAME, PASSWORD);

    BatchPoints batchPoints = BatchPoints.database(DATABASE_NAME)
            .retentionPolicy("autogen")
            .build();

    public void writeToInflux(String pageName) {
        getAllTimings();

        influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
        Point point = Point.measurement("client-side")
                .time(System.currentTimeMillis(), TimeUnit.SECONDS)
                .tag("projectName", PROJECT_NAME)
                .tag("scenario", SCENARIO_NAME)
                .tag("env", ENV_NAME)
                .tag("browser", BROWSER_NAME)
                .tag("page", pageName)
                .tag("buildID", BUILD_ID)
                .addField("latency", this.getLatency())
                .addField("backend_response", this.getBackEnd_response())
                .addField("timeToInteract", this.getTimeToInteract())
                .addField("timeToLoad", this.getTimeToLoad())
                .addField("onload", this.getLoad())
                .addField("totalTime", this.getTotal())
                .build();
        batchPoints.point(point);
        influxDB.write(batchPoints);

    }

    public Map<String, Object> getAllTimings() {

        JavascriptExecutor jsrunner = (JavascriptExecutor) WebDriverHolder.getDriver();
        timings = (Map<String, Object>) jsrunner.executeScript(javaScriptForPerformance);
        return timings;
    }

    private Long getAnTime(String name) {
        return (Long) timings.get(name);
    }

    public Long getNavigationStart() {
        return getAnTime("navigationStart");
    }

    public Long getResponseStart() {
        return getAnTime("responseStart");
    }

    public Long getResponseEnd() {
        return getAnTime("responseEnd");
    }

    public Long getDomLoading() {
        return getAnTime("domLoading");
    }

    public Long getDomInteractive() {
        return getAnTime("domInteractive");
    }

    public Long getDomComplete() {
        return getAnTime("domComplete");
    }

    public Long getLoadEventStart() {
        return getAnTime("loadEventStart");
    }

    public Long getLoadEventEnd() {
        return getAnTime("loadEventEnd");
    }

    //results
    public Long getLatency() {
        return getResponseStart() - getNavigationStart();
    }

    public Long getBackEnd_response() {
        return getResponseEnd() - getResponseStart();
    }

    public Long getTimeToInteract() {
        return getDomInteractive() - getDomLoading();
    }

    public Long getTimeToLoad() {
        return getDomComplete() - getDomInteractive();
    }

    public Long getLoad() {
        return getLoadEventEnd() - getLoadEventStart();
    }

    public Long getTotal() {
        return getLoadEventEnd() - getNavigationStart();
    }

}
