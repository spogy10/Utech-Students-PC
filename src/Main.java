import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Main extends Application {
    boolean url1 = false, url2 = false, url3 = false, url4 = false;

  VBox vb = new VBox();

  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    vb.setId("root");

    WebView  browser = new WebView();
    WebEngine engine = browser.getEngine();
    String url = "https://evision.utech.edu.jm/sipr/sits.urd/run/siw_lgn";
    //String url = "https://evision.utech.edu.jm/sipr/sits.urd/run/siw_lgn", url1 = "https://evision.utech.edu.jm/sipr/sits.urd/run/SIW_LGN", url2 = "https://evision.utech.edu.jm/sipr/sits.urd/run/SIW_PQS", url3 = "", url4 = "";
    engine.load(url);
    
// do something once the website is loaded
    engine.getLoadWorker().stateProperty().addListener((ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) -> {
        if (newValue != Worker.State.SUCCEEDED) {
            return;
        }
        if(url4){
            engine.executeScript("document.getElementsByClassName('portallink')[11].getElementsByTagName('a')[0].click()");
            url4 = false;
        }
        if(url3){
            engine.executeScript("document.getElementById('STU1').click();");
            url3 = false;
            url4 = true;
        }
        //engine.excecuteScript("var x = document.getElementById('MUA_CODE.DUMMY.MENSYS')")
        //engine.load("http://yahoo.com");
        if (engine.getLocation().equals(url)){
            engine.executeScript("var x = true; document.getElementById('MUA_CODE.DUMMY.MENSYS').value = '1500747'; document.getElementById('PASSWORD.DUMMY.MENSYS').value = 'pass5utech'; document.getElementsByName('BP101.DUMMY_B.MENSYS.1')[0].click();");
            url1 = true;
        }

        if(url1){
            engine.executeScript("document.getElementsByName('ANSWER.MUQ.MENSYS.1')[0].value = '050997'; document.getElementsByName('STORE.DUMMY_BUTT.MENSYS.1')[0].click();");
            url1 = false;
            url2 = true;
        }
        
        if(url2){
            url2 = false;
            url3 = true;
        }
            
    });
    
    //vb.setPadding(new Insets(30, 50, 50, 50));
    vb.setSpacing(0);
    vb.setAlignment(Pos.CENTER);
    vb.getChildren().addAll(browser);

    Scene scene = new Scene(vb);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
//  public String locationCheck(WebEngine engine, String oldURL){
//      
//      String historyUrl="";
//      engine.getHistory();
//      if (mWebBackForwardList.getCurrentIndex() > 0) 
//      historyUrl = mWebBackForwardList.getItemAtIndex(mWebBackForwardList.getCurrentIndex()-1).getUrl();
//      String newURL = engine.getLocation();
//      
//      return newURL;
//  }

}
