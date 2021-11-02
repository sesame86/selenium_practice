package test_selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class KhTestSelenium {

	public KhTestSelenium() {
	}

	public static void main(String[] args) {
		System.getProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver dr1 = new ChromeDriver();   // 크롬창을 열기위한 준비->열기
		WebDriverWait w1 = new WebDriverWait(dr1, 10);   // 위 크롬창이 실제 열릴때까지 10초정도 기다려줌.
		JavascriptExecutor jsexe = (JavascriptExecutor)dr1;
		
		dr1.get("https://iei.or.kr/login/login.kh"); // 열린 크롬창에 url 이동함
		WebElement e1 = dr1.findElement(By.id("id"));
		if(e1 != null) {  // 로그인 필요한 상태
			WebElement e2 = dr1.findElement(By.id("password"));
			e1.sendKeys("kyy806");
			e2.sendKeys("ckaro0806@@");
			
			jsexe.executeScript("fnLogin()");			
		}
		// 로그인 완료된 상태
		// 페이지 로딩될때까지 기다려야 다음 js 실행됨
		w1.until(ExpectedConditions.titleContains("KH정보교육원 - 마이페이지"));
		jsexe.executeScript("location.href='/login/currBoard.kh'");
		
		// 페이지 로딩될때까지 기다려야 다음 js 실행됨
		w1.until(ExpectedConditions.titleContains("우리반 게시판"));
		jsexe.executeScript("fnWriteForm()");
		
		// title element이라것을 클릭할 수 있는지 확인 될때까지 기다림
		w1.until(ExpectedConditions.elementToBeClickable(By.id("title")));
		WebElement e3 = dr1.findElement(By.id("title"));
		e3.sendKeys("제목입력테스트");
		
		// wysiwyg 프레임으로 이동
		dr1.switchTo().frame("tx_canvas_wysiwyg");
		// 내용입력창
		WebElement e4 = dr1.findElement(By.className("tx-content-container"));
		// 내용입력
		e4.sendKeys("내용입력~ 아이프레임사용된 입력창 -frame~~~ 이동");
		
		// 부모프레임. 원본프레임으로 이동
		dr1.switchTo().parentFrame();
		// 완료버튼누른 행동
		jsexe.executeScript("javascript:fnRegister();");
		
		// popup 창으로 이동해 확인 
		dr1.switchTo().alert().accept();
		
		// 크롬창 닫고 resource 닫기
		dr1.close();
	}
}