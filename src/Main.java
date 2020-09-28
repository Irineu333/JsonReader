import java.util.*;
import org.json.JSONException;

public class Main
{
	public static void main(String[] args)
	{
        String json = "";
        System.out.println("Digite um JSON ou sair");
        while(!json.equals("sair"))
        {
            
            Scanner scanner = new Scanner(System.in);
            json = scanner.next();
            if(json.equals("sair"))
                break;
            JsonFactor jsonFactor = new JsonFactor();
            jsonFactor.refact(json);

            String result = jsonFactor.getEstrutura();

            System.out.println(result);
        }
        System.out.print("\nTerminado");
	}

}
