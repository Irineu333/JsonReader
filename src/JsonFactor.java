
import java.util.List;
import java.util.ArrayList;

public class JsonFactor
{
    private final List<String> estrutura;

    public JsonFactor()
    {
        estrutura = new ArrayList<>();
    }

    public String getEstrutura()
    {
        String retorno = "";
        for (String string : estrutura)
        {
            retorno += string + "\n";
        }
        return retorno;
    }

    public void refact(String json)
    {
        try
        {
            Object[] args = {new org.json.JSONTokener(json).nextValue()};
            refactor(args, 0, -1);
        }
        catch (org.json.JSONException e)
        {
            System.out.print(e.getMessage());
        }
    }

    private void refactor(org.json.JSONObject object, int index) throws org.json.JSONException
    {
        java.util.Iterator<String> keys = object.keys();
        while (keys.hasNext())
        {
            String key = keys.next();
            Object factor = object.get(key);
            Object[] args = {factor, key};
            refactor(args, index, -1); 
        }
    }

    private void refactor(org.json.JSONArray array, int index) throws org.json.JSONException
    {
        for (int i = 0; i < array.length(); i++)
        {
            Object[] args = {array.get(i)};
            refactor(args, index, i);
        }
    }

    private void refactor(Object[] args, int index, int pos) throws org.json.JSONException
    {
        String tab = this.tab(index, pos);
        Object factor = args[0];
        String key = "";

        if (args.length == 2)
            key = (String) args[1] + " = ";

        if (factor instanceof org.json.JSONArray)
        {
            estrutura.add(tab + "array[]");
            org.json.JSONArray array = (org.json.JSONArray) factor;
            refactor(array, index + 1);

        }
        else if (factor instanceof org.json.JSONObject)
        {
            estrutura.add(tab + "object{}");
            org.json.JSONObject object = (org.json.JSONObject) factor;
            refactor(object, index + 1);
        }
        else if (factor instanceof String)
        {
            String string = (String) factor;
            estrutura.add(tab + "String: " + key + "\"" + string + "\"");

        }
        else if (factor instanceof Double)
        {
            Double d = (Double) factor;
            estrutura.add(tab + "double: " + key + d);

        }
        else if (factor instanceof Long)
        {
            Long l = (Long) factor;
            estrutura.add(tab + "long: " + key + l);
        }
        else if (factor instanceof Integer)
        {
            Integer inteiro = (Integer) factor;
            estrutura.add(tab + "int: " + inteiro);

        }
        else if (factor instanceof Boolean)
        {
            Boolean bool = (Boolean) factor;
            estrutura.add(tab + "boolean: " + key + bool);
        }
        else
        {
            System.out.print("\nErro: " + factor);
        }
    }

    private String tab(int index, int pos)
    {
        String tab = "";

        //recuo
        for (int i =0; i < index; i++)
        {
            tab += "\t";
        }

        //posição
        if (pos > -1)
        {
            tab = tab.substring(1);
            tab += "  " + pos + " ";
        }

        return tab;
    }

}
