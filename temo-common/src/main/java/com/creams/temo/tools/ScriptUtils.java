package com.creams.temo.tools;

import com.creams.temo.entity.FuncToolsEntity;
import org.springframework.stereotype.Component;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Map;

@Component
public class ScriptUtils {

    public Object invokeScript(FuncToolsEntity funcToolsEntity, Map map) throws ScriptException, NoSuchMethodException {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName(funcToolsEntity.getFuncLang());
        engine.eval(funcToolsEntity.getFuncScript());
        Invocable inv = (Invocable) engine;
        Object[] params = new String[map.size()];
        String[] objects = funcToolsEntity.getFuncParams().split(",");
        for (int i=0;i<objects.length;i++) {
            String[] key = objects[i].split("#");
            params[i]=map.get(key[0]);
        }
        return inv.invokeFunction(funcToolsEntity.getFuncName(), params);
    }
}
