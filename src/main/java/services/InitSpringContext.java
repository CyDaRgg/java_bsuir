package services;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InitSpringContext
{
    private static ApplicationContext context;


   static {
       context = new ClassPathXmlApplicationContext(new String[]{"springContext.xml"});
   }

    public static  ApplicationContext getContext() {
        return context;
    }

    public static void setContext(ApplicationContext context) {
        InitSpringContext.context = context;
    }
}
