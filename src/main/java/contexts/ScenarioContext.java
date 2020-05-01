package contexts;

import java.util.HashMap;
import java.util.Map;

import static java.util.Optional.ofNullable;

public class ScenarioContext {

    private Map<Context, Object> context;

    public ScenarioContext() {
        context = new HashMap<>();
    }

    public <T> void setContext(Context key, T value) {
        context.put(key, value);
    }

    public <T> T getContext(Context key) {
        return ofNullable(context.get(key)).map(obj -> (T) obj).orElse(null);
    }

    public enum Context {
        NUMBER_OF_ARTICLES_WITH_GEO_POINT_TODAY,
        NUMBER_OF_ARTICLES_WITH_GEO_POINT_TOMORROW;
    }
}
