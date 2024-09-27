package com.javapub.flowable.myflowable.task;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

/**
 * @author keda
 * @date 2024/9/27 09:35:13
 */
@Slf4j
@Component
public class ForceDispatchTask implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        log.info("力量调派");
    }
}
