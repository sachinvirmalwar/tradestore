
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;


public class TradeApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConfigurableApplicationContext ctx = SpringApplication.run(TradeApplication.class, args);
		ctx.close();
	}

}
