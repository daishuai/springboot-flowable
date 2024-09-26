package com.javapub.flowable.myflowable.task;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author keda
 * @date 2024/9/26 18:41:14
 */
@Slf4j
@Component
public class AssignTask implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) {
        log.info("分配接警员");
        String incidentCode = delegateExecution.getVariable("incidentCode", String.class);
        Boolean autoAssign = delegateExecution.getVariable("autoAssign", Boolean.class);
        if (Boolean.TRUE.equals(autoAssign)) {
            delegateExecution.setVariable("dispatchers", List.of("zhangsan", "heqifeng"));
            delegateExecution.setVariable("assignResult", "success");
        } else {
            delegateExecution.setVariable("assignResult", "failed");
        }
    }
}
