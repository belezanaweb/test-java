package test.blz.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.catalina.connector.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

public class GracefulShutdown implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {

    private static final Logger log = LoggerFactory.getLogger(GracefulShutdown.class);

    private Connector connector;

    private final Long shutdownTimeout;

    private final TimeUnit unit;

    public GracefulShutdown (final Long shutdownTimeout, final TimeUnit unit) {
        this.shutdownTimeout = shutdownTimeout;
        this.unit = unit;
    }

    @Override
    public void customize(final Connector connector) {
        this.connector = connector;

    }

    @Override
    public void onApplicationEvent(final ContextClosedEvent event) {
        if (this.connector == null) {
            return;
        }
        awaitTermination(this.connector);
    }

    private void awaitTermination(final Connector connector) {
        log.info("graceful shutdown");
        connector.pause();

        final Executor executor = connector.getProtocolHandler().getExecutor();
        if (executor instanceof ThreadPoolExecutor) {
            final ThreadPoolExecutor threadPoolExecutor = ((ThreadPoolExecutor) executor);
            try {
                log.info("activeThreads"+ threadPoolExecutor.getActiveCount() + "***Await Termination!!!***");
                threadPoolExecutor.shutdown();
                if (!threadPoolExecutor.awaitTermination(shutdownTimeout, unit)) {
                    log.warn("Tomcat thread pool did not shut down gracefully within $shutdownTimeout $unit. Proceeding with forceful shutdown");
                } else {
                    log.info("***Terminado com sucesso!***");
                    log.info("->" + threadPoolExecutor.getActiveCount());
                }
            } catch (final InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        log.info("graceful shutdown completed");
    }
}
