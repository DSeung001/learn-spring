package hello.servlet.web.frontController;

import java.util.HashMap;
import java.util.Map;

// 스프링에는 model & view 기능이 존재
// 현재 스프링을 사용안하는 컨샙이므로 lombok 사용 x
public class ModelView {
    private String viewName;
    private Map<String, Object> model = new HashMap<>();

    public ModelView(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
