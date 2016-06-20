package libraries;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by hdhamee on 6/16/16.
 */
public class NashornJavaScriptEngine {
    //Java 8 comes with new Nashorn JavaScript engine which allows developing and running certain kinds of JavaScript
    // applications on JVM.
    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName( "JavaScript" );

        System.out.println( engine.getClass().getName() );
        System.out.println( "Result:" + engine.eval( "function f() { return 1; }; f() + 1;" ) );
    }
}
