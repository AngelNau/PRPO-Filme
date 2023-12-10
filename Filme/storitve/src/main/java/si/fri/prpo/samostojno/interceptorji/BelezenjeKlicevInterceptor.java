package si.fri.prpo.samostojno.interceptorji;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import si.fri.prpo.samostojno.anotacije.BeleziKlice;
import si.fri.prpo.samostojno.zrna.BelezenjeKlicevZrno;

import java.util.logging.Logger;

@Interceptor
@BeleziKlice
public class BelezenjeKlicevInterceptor {
    private final Logger log = Logger.getLogger(BelezenjeKlicevInterceptor.class.getName());
    @Inject
    private BelezenjeKlicevZrno belezenjeKlicevZrno;

    @AroundInvoke
    public Object getStKlicev(InvocationContext ic) throws Exception{
        int steviloKlicev = belezenjeKlicevZrno.increaseCounter();
        log.info("Number of calls: " + steviloKlicev);
        return ic.proceed();
    }
}