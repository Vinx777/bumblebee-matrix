package com.bumblebee.config

import com.bumblebee.util.CommonLogging
import org.springframework.context.annotation.Configuration

import java.util.concurrent.ThreadPoolExecutor

@Configuration
class ThreadPoolConfiguration extends CommonLogging {

  import org.springframework.context.annotation.Bean
  import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

  @Bean(name = Array("threadPoolTaskExecutor"))
  def threadPoolTaskExecutor: ThreadPoolTaskExecutor = {
    val threadPoolTaskExecutor = new ThreadPoolTaskExecutor
    threadPoolTaskExecutor.setCorePoolSize(10)
    threadPoolTaskExecutor.setMaxPoolSize(10)
    threadPoolTaskExecutor.setQueueCapacity(100)
    threadPoolTaskExecutor.setKeepAliveSeconds(60)
    threadPoolTaskExecutor.setAwaitTerminationSeconds(5)
    threadPoolTaskExecutor.setThreadNamePrefix("async-thread")
    // RejectedExecutionHandler: behaviour of when reach max-size
    // CallerRunsPolicy: Run task on current thread
    threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy())
    threadPoolTaskExecutor.initialize()
    threadPoolTaskExecutor
  }
}
