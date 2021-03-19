package pl.skiba.springimageupload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Prosty test działania spring security
@RestController //dalej możliwość dostepu z przeglądarki lub klienta http
public class TestApi {

    @GetMapping("/test1")
    public String test1(){
        return "test1";
    }
    @GetMapping("/test2")
    public String test2(){
        return "test2";
    }
    @GetMapping("/test3")
    public String test3(){
        return "test3";
    }
}
