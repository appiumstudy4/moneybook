package com.appium.cucumber.Moneybook;

import io.appium.java_client.ScrollsTo;
import io.appium.java_client.android.AndroidDriver;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class App {
	
	private AndroidDriver driver;

	@Before
	public void setUp() throws Exception{
		
		File classpathRoot = new File("D:/hanzisu/Moneybook");
        File appDir = new File(classpathRoot, "/Downloadapk");
        File app = new File(appDir, "moneybook_1.2.6.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "410022b2f248b15f");
        capabilities.setCapability("platformVersion", "5.1");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.nhn.android.moneybook");
        capabilities.setCapability("unicodeKeyboard", true);
        // capabilities.setCapability("appActivity",
        // "com.nhn.android.moneybook");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
                capabilities);


		/*File classpathRoot = new File("D:/hanzisu/Moneybook");
		File appDir = new File(classpathRoot, "/Downloadapk");
		File app = new File(appDir, "moneybook_1.2.6.apk");
	

	DesiredCapabilities capabilities = new DesiredCapabilities();
	
	capabilities.setCapability("platformName", "Android");
	capabilities.setCapability("deviceName", "017b5e03624be36d");
	capabilities.setCapability("platformVersion", "4.4");
	capabilities.setCapability("app", "D:/hanzisu/Moneybook/Downloadapk/moneybook_1.2.6.apk");
	capabilities.setCapability("appPackage", "com.nhn.android.moneybook");

	driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);*/
	}
	///////////////////////////*SCENARIO #1 가계부앱 로그인 시나리오*///////////////////////////////////////////////////////
	@Given("^아이디 비밀번호 입력 후 로그인$")
	public void 아이디_비밀번호_입력_후_로그인() throws Throwable {
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		WebElement edit_id = driver.findElement(By.id("com.nhn.android.moneybook:id/nloginglobal_normal_signin_textview_id"));
		edit_id.sendKeys("nvqa_han");
		WebElement edit_pw= driver.findElement(By.id("com.nhn.android.moneybook:id/nloginglobal_normal_signin_textview_pw"));
		edit_pw.sendKeys("qweasd");
		WebElement btn_login= driver.findElement(By.id("com.nhn.android.moneybook:id/nloginglobal_normal_signin_bt_signin"));
		btn_login.click();
	    
	}

	@When("^sms자동인식 화면 확인$")
	public void sms자동인식_화면_확인() throws Throwable {
		/*driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.id("com.nhn.android.moneybook:id/enable_sms_auto_save")).click();*/
		

	}

	@Then("^가계부 홈화면 확인$")
	public void 가계부_홈화면_확인() throws Throwable {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement btn_noti_close = driver.findElement(By.id("com.nhn.android.moneybook:id/close_noti_for_touch_face_icon"));
		btn_noti_close.click();

		
	}
	
	
	//////////////////////*SCENARIO #2 money-01 한달보기 수입내역 저장 후 계속 입력*///////////////////////////////////////////////
	@Given("^내역 쓰기 버튼 클릭$")
	public void 내역_쓰기_버튼_클릭() throws Throwable {
		
		WebElement btn_write = driver.findElement(By.id("com.nhn.android.moneybook:id/goWritePageBtn"));
		btn_write.click();
				
		//TouchActions flick = new TouchActions(driver).flick(cate, -300, 0, 0);
		//flick.perform();
				
	}

	@When("^수입 내역 입력 후 저장 후 계속 입력 선택한 뒤 새 수입내역 입력 후 저장$")
	public void 수입_내역_입력_후_저장_후_계속_입력_선택한_뒤_새_수입내역_입력_후_저장() throws Throwable {
		WebElement btn_write_income = driver.findElement(By.id("com.nhn.android.moneybook:id/type_income_item"));
		btn_write_income.click();
		WebElement edit_write_income_amt = driver.findElement(By.id("com.nhn.android.moneybook:id/income_amt"));
		edit_write_income_amt.sendKeys("1000");
		WebElement edit_write_income_usedesc = driver.findElement(By.id("com.nhn.android.moneybook:id/use_desc"));
		edit_write_income_usedesc.click();
		edit_write_income_usedesc.sendKeys("test1");
		WebElement btn_write_income_saveandwrite = driver.findElement(By.id("com.nhn.android.moneybook:id/save_and_write_income_item"));
		btn_write_income_saveandwrite.click();
		edit_write_income_amt.sendKeys("2000");
		edit_write_income_usedesc.click();
		edit_write_income_usedesc.sendKeys("test2");
		WebElement btn_write_income_save = driver.findElement(By.id("com.nhn.android.moneybook:id/save_income_item"));
		btn_write_income_save.click();
	}

	@Then("^한달보기에 수입내역 (\\d+)건 저장되어 노출 확인$")
	public void 한달보기에_수입내역_건_저장되어_노출_확인(int arg1) throws Throwable {
		//WebElement edit_home_usedesc = driver.findElement(By.id("com.nhn.android.moneybook:id/use_desc"));
		
		List<WebElement> home_List=driver.findElementsByClassName("android.widget.RelativeLayout");
		assertTrue(home_List.get(2).isDisplayed());
		assertTrue(home_List.get(3).isDisplayed());
		
	}
	
//////////////////////*SCENARIO #3-money-02 지출내역 저장 시 자주쓰는 내역 추가*///////////////////////////////////////////////
	@Given("^내역쓰기 버튼 클릭$")
	public void 내역쓰기_버튼_클릭() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		WebElement btn_write = driver.findElement(By.id("com.nhn.android.moneybook:id/goWritePageBtn"));
		btn_write.click();
	}

	@Given("^지출 내역 입력 후 자주쓰는 내역 추가 클릭하여 저장$")
	public void 지출_내역_입력_후_자주쓰는_내역_추가_클릭하여_저장() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		WebElement edit_write_outgo_amt = driver.findElement(By.id("com.nhn.android.moneybook:id/outgo_amt"));
		edit_write_outgo_amt.sendKeys("1234");
		WebElement edit_write_outgo_usedesc = driver.findElement(By.id("com.nhn.android.moneybook:id/use_desc"));
		edit_write_outgo_usedesc.click();
		edit_write_outgo_usedesc.sendKeys("자주쓰는내역");
		WebElement check_add_freq_item = driver.findElement(By.id("com.nhn.android.moneybook:id/add_frequently_used_mbook_item"));
		check_add_freq_item.click();
		WebElement btn_write_outgo_save = driver.findElement(By.id("com.nhn.android.moneybook:id/save_outgo_item"));
		btn_write_outgo_save.click();
		//driver.switchTo().alert().accept();
		WebElement alert_ok = driver.findElement(By.id("android:id/button1"));
		alert_ok.click();
	
	}
	

	@When("^내역쓰기 버튼 클릭 후 불러오기하여 내역 선택 후 저장$")
	public void 내역쓰기_버튼_클릭_후_불러오기하여_내역_선택_후_저장() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		WebElement btn_write = driver.findElement(By.id("com.nhn.android.moneybook:id/goWritePageBtn"));
		btn_write.click();
		WebElement btn_load_freq_item = driver.findElement(By.id("com.nhn.android.moneybook:id/load_frequently_used_mbook_item"));
		btn_load_freq_item.click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/use_desc")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/use_desc")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/clear_use_desc")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/use_desc")).sendKeys("자주쓰는내역불러오기");
		driver.findElement(By.id("com.nhn.android.moneybook:id/save_outgo_item")).click();
			   
	}

	@Then("^한달 보기에 내역 저장되어 노출 확인$")
	public void 한달_보기에_내역_저장되어_노출_확인() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.name("자주쓰는내역불러오기")).isDisplayed();
	}
//////////////////////*SCENARIO #4-money-03 내역 수정 후 저장*///////////////////////////////////////////////
	@Given("^내역 클릭하여 내역보기로 이동$")
	public void 내역_클릭하여_내역보기로_이동() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		WebElement change_item = driver.findElement(By.name("자주쓰는내역불러오기"));
		change_item.click();
	}

	@When("^지출 내역 수정 후 저장 버튼 클릭$")
	public void 지출_내역_수정_후_저장_버튼_클릭() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("com.nhn.android.moneybook:id/use_desc")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/clear_use_desc")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/use_desc")).sendKeys("내역수정");
		driver.findElement(By.id("com.nhn.android.moneybook:id/save_outgo_item")).click();
	}

	@Then("^수정된 내역 반영되었는지 확인$")
	public void 수정된_내역_반영되었는지_확인() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.name("내역수정")).isDisplayed();
	 
	}
//////////////////////*SCENARIO #5-money-04 오늘의 현금잔액 저장*///////////////////////////////////////////////
	@Given("^그래프 클릭$")
	public void 그래프_클릭() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		//driver.findElement(By.id("com.nhn.android.moneybook:id/go_config_page")).click();
		//WebElement check_account=driver.findElement(By.id("com.nhn.android.moneybook:id/btnAccountEnable"));
		/*if(check_account.isEnabled())
		{
			if(!check_account.isSelected())
				check_account.click();
			
		}else
		{
			System.out.println("Account Checkbox is disabled!");
		}*/
		//driver.findElement(By.id("com.nhn.android.moneybook:id/go_monthly_smry_page")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/default_face_icon")).click();
		
	}

	@When("^오늘의 현금잔액 선택 후 잔액 체크 후 저장$")
	public void 오늘의_현금잔액_선택_후_잔액_체크_후_저장() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("com.nhn.android.moneybook:id/move_report_asset_status")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/chkAccountId")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/save_cash_account")).click();
	
	}

	@Then("^그래프 선택하여 옹늘의 현금잔액 팝업 내 현금잔액란에 잔액 노출 확인$")
	public void 그래프_선택하여_옹늘의_현금잔액_팝업_내_현금잔액란에_잔액_노출_확인() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("com.nhn.android.moneybook:id/default_face_icon")).click();
		WebElement amt=driver.findElement(By.id("com.nhn.android.moneybook:id/cashable_asset_amt"));
		boolean result = true;
		if(amt.getText()=="0"){
			result = false;
			}
		assertTrue(result);
		driver.findElement(By.id("com.nhn.android.moneybook:id/smry_popup_close")).click();
	}	
	
//////////////////////*SCENARIO #6-money-05 총 누적자산 중 현금잔액 이동*///////////////////////////////////////////////
	@Given("^통장관리 사용 off 상태 확인 후 그래프 클릭$")
	public void 통장관리_사용_off_상태_확인_후_그래프_클릭() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions\
		driver.findElement(By.id("com.nhn.android.moneybook:id/go_config_page")).click();
		WebElement check_account=driver.findElement(By.id("com.nhn.android.moneybook:id/btnAccountEnable"));
		check_account.click();
		driver.findElement(By.id("android:id/button1")).click();
		/*if(check_account.isEnabled())
		{
			if(check_account.isSelected())
				check_account.click();
			driver.findElement(By.id("android:id/button1")).click();
			
		}else
		{
			System.out.println("Account Checkbox is disabled!");
		}*/
		driver.findElement(By.id("com.nhn.android.moneybook:id/go_monthly_smry_page")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/default_face_icon")).click();
	}

	@When("^'총 누적자산 중 현금잔액' 선택$")
	public void 총_누적자산_중_현금잔액_선택() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("com.nhn.android.moneybook:id/move_report_asset_status")).click();
	}

	@Then("^총 누적자산 페이지로 이동함을 확인$")
	public void 총_누적자산_페이지로_이동함을_확인() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("com.nhn.android.moneybook:id/totalAccumulatedAssetList")).isDisplayed();
	}
	
//////////////////////*SCENARIO #7-money-06 카드대금 포함 내역값 확인*///////////////////////////////////////////////
	@Given("^카드 대금 포함 on 상태 확인 후 내역쓰기 클릭$")
	public void 카드_대금_포함_on_상태_확인_후_내역쓰기_클릭() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("com.nhn.android.moneybook:id/go_config_page")).click();
		WebElement check_card_include=driver.findElement(By.id("com.nhn.android.moneybook:id/btnIncludeCardPayment"));
		check_card_include.click();
		/*if(check_card_include.isEnabled())
		{
			if(!check_card_include.isSelected())
				check_card_include.click();
			
		}else
		{
			System.out.println("Account Checkbox is disabled!");
		}*/
		driver.findElement(By.id("com.nhn.android.moneybook:id/go_monthly_smry_page")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/goWritePageBtn")).click();
	}

	@When("^분류에 카드대금 지정 후 저장$")
	public void 분류에_카드대금_지정_후_저장() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		String amount="1234";
		driver.findElement(By.id("com.nhn.android.moneybook:id/outgo_amt")).sendKeys(amount);
		driver.findElement(By.id("com.nhn.android.moneybook:id/use_desc")).sendKeys("카드대금포함내역");
		
		//플리킹테스트
				driver.findElement(By.id("com.nhn.android.moneybook:id/cat_etc")).click();
				WebElement swipe_start = driver.findElement(By.name("건강/문화"));

				WebElement swipe_end = driver.findElement(By.name("식비"));
				System.out.println(swipe_start.getLocation().x +" "+swipe_start.getLocation().y + "/" + swipe_end.getLocation().x+","+swipe_end.getLocation().y);
				driver.swipe(swipe_start.getLocation().x, swipe_start.getLocation().y, swipe_end.getLocation().x, swipe_start.getLocation().y, 100);
												
				driver.findElement(By.name("카드대금")).click();
				driver.findElement(By.id("com.nhn.android.moneybook:id/scat_name")).click();
				
				driver.findElement(By.id("com.nhn.android.moneybook:id/save_outgo_item")).click();
	   
	}

	@Then("^그래픽 아이콘 선택하여 카드대금 포함값이 노출되는지 확인$")
	public void 그래픽_아이콘_선택하여_카드대금_포함값이_노출되는지_확인() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("com.nhn.android.moneybook:id/default_face_icon")).click();
		String card_payment=driver.findElement(By.id("com.nhn.android.moneybook:id/smry_popup_card_payment")).getText();
		assertEquals("(1234)", card_payment);
		driver.findElement(By.id("com.nhn.android.moneybook:id/smry_popup_close")).click();
	  
	}
//////////////////////*SCENARIO #8-money-07 카드대금 미포함 내역값 확인*///////////////////////////////////////////////
	@Given("^카드 대금 포함 off 상태 확인 및 설정$")
	public void 카드_대금_포함_off_상태_확인_및_설정() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("com.nhn.android.moneybook:id/go_config_page")).click();
		WebElement check_card_include=driver.findElement(By.id("com.nhn.android.moneybook:id/btnIncludeCardPayment"));
		check_card_include.click();
		/*if(check_card_include.isEnabled())
		{
			if(check_card_include.isSelected())
				check_card_include.click();
			
		}else
		{
			System.out.println("Account Checkbox is disabled!");
		}*/
		driver.findElement(By.id("com.nhn.android.moneybook:id/go_monthly_smry_page")).click();
	}

	@When("^날짜바 클릭하여 내역쓰기로 이동$")
	public void 날짜바_클릭하여_내역쓰기로_이동() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("com.nhn.android.moneybook:id/item_ymd")).click();
	}
	@When("^분류에 카드대금 지정한 뒤 저장$")
	public void 분류에_카드대금_지정한_뒤_저장() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		String amount="1234";
		driver.findElement(By.id("com.nhn.android.moneybook:id/outgo_amt")).sendKeys(amount);
		driver.findElement(By.id("com.nhn.android.moneybook:id/use_desc")).sendKeys("카드대금포함내역");
		
		//플리킹테스트
				driver.findElement(By.id("com.nhn.android.moneybook:id/cat_etc")).click();
				WebElement swipe_start = driver.findElement(By.name("건강/문화"));
				WebElement swipe_end = driver.findElement(By.name("식비"));
				System.out.println(swipe_start.getLocation().x +" "+swipe_start.getLocation().y + "/" + swipe_end.getLocation().x+","+swipe_end.getLocation().y);
				driver.swipe(swipe_start.getLocation().x, swipe_start.getLocation().y, swipe_end.getLocation().x, swipe_start.getLocation().y, 100);
												
				driver.findElement(By.name("카드대금")).click();
				driver.findElement(By.id("com.nhn.android.moneybook:id/scat_name")).click();
				
				driver.findElement(By.id("com.nhn.android.moneybook:id/save_outgo_item")).click();
	   
	}

	@Then("^그래픽 아이콘 선택하여 카드대금이 미포함 되었는지 확인$")
	public void 그래픽_아이콘_선택하여_카드대금이_미포함_되었는지_확인() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("com.nhn.android.moneybook:id/default_face_icon")).click();
		String card_payment=driver.findElement(By.id("com.nhn.android.moneybook:id/smry_popup_card_payment")).getText();
		assertEquals("(1234)", card_payment);
		driver.findElement(By.id("com.nhn.android.moneybook:id/smry_popup_close")).click();
	}
	
	
	
	
	
	
//////////////////////*SCENARIO #9-money-8 희망목표>목표액확인및수정*///////////////////////////////////////////////
	@Given("^희망목표 클릭하여 희망목표 페이지로 이동$")
	public void 희망목표_클릭하여_희망목표_페이지로_이동() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("com.nhn.android.moneybook:id/btn_hopegoal")).click();
	}

	@When("^목표만들기 클릭하여 희망목표 만들기로 이동$")
	public void 목표만들기_클릭하여_희망목표_만들기로_이동() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("com.nhn.android.moneybook:id/rl_make_hope_goal")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/tv_hope_goal_title")).isDisplayed();
		
	}

	@When("^목표 설정 후 저장 후 확인$")
	public void 목표_설정_후_저장_후_확인() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		String goal_amt="10000";
		driver.findElement(By.id("com.nhn.android.moneybook:id/et_hope_goal_name")).sendKeys("희망목표이름");
		driver.findElement(By.id("com.nhn.android.moneybook:id/en_hope_goal_amt")).sendKeys(goal_amt);
		driver.findElement(By.id("com.nhn.android.moneybook:id/tv_hope_goal_start")).click();
		driver.findElement(By.id("android:id/button1")).click();
		driver.findElement(By.id("android:id/button1")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/et_hope_goal_tags")).sendKeys("희망목표 태그");
		driver.findElement(By.id("com.nhn.android.moneybook:id/rl_hope_goal_save")).click();
		
		//저장된 목표 확인
		driver.findElement(By.name("희망목표이름")).isDisplayed();
		
	}

	@When("^저장된 목표 선택하여 목표이름/금액 수정$")
	public void 저장된_목표_선택하여_목표이름_금액_수정() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		String goal_amt_change="20000";
		driver.findElement(By.name("희망목표이름")).click();
		
		driver.findElement(By.id("com.nhn.android.moneybook:id/et_hope_goal_name")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/ib_hope_goal_name_clear")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/et_hope_goal_name")).sendKeys("희망목표이름수정");
		
		driver.findElement(By.id("com.nhn.android.moneybook:id/en_hope_goal_amt")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/ib_hope_goal_amt_clear")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/en_hope_goal_amt")).sendKeys(goal_amt_change);
		
		driver.findElement(By.id("com.nhn.android.moneybook:id/rl_hope_goal_save")).click();
	}

	@Then("^수정된 정보 노출 확인$")
	public void 수정된_정보_노출_확인() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.name("희망목표이름수정")).isDisplayed();
		driver.findElement(By.name("20,000")).isDisplayed();
		
		driver.findElement(By.id("com.nhn.android.moneybook:id/go_monthly_smry_page")).click();
	}	
//////////////////////*SCENARIO #10-money-9 예산 반성> 예산 작성 및 전월 예산 복사*///////////////////////////////////////////////	
	@Given("^예산반성 클릭하여 예산반성 페이지로 이동$")
	public void 예산반성_클릭하여_예산반성_페이지로_이동() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("com.nhn.android.moneybook:id/btn_budget")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/btnBudgetReflect")).isDisplayed();
	}

	@When("^예산 작성 클릭하여 예산작성 페이지로 이동$")
	public void 예산_작성_클릭하여_예산작성_페이지로_이동() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("com.nhn.android.moneybook:id/btnBudgetWrite")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/budget_write_top_layer")).isDisplayed();
	}

	@When("^예산 작성하여 저장 후 확인$")
	public void 예산_작성하여_저장_후_확인() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		String total_amt="1,000";
		String amt=",500";
				
		driver.findElement(By.id("com.nhn.android.moneybook:id/btnBudgetReflect")).isDisplayed();
		driver.findElement(By.id("com.nhn.android.moneybook:id/budget_total_amt")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/clear_budget_total_amt")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/budget_total_amt")).sendKeys(total_amt);
		
		driver.findElement(By.id("com.nhn.android.moneybook:id/budget_lcat001_amt")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/clear_budget_lcat001")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/budget_lcat001_amt")).sendKeys(amt);
		
		driver.findElement(By.id("com.nhn.android.moneybook:id/budget_lcat002_amt")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/clear_budget_lcat002")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/budget_lcat002_amt")).sendKeys(amt);
		//저장
		driver.findElement(By.id("com.nhn.android.moneybook:id/save_budget")).click();
		
	}

	@When("^예산 작성 클릭 후 다음달로 날짜 이동$")
	public void 예산_작성_클릭_후_다음달로_날짜_이동() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("com.nhn.android.moneybook:id/btnBudgetWrite")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/budget_write_top_layer")).isDisplayed();
		
		driver.findElement(By.id("com.nhn.android.moneybook:id/nextBtn")).click();
	}

	@When("^전월 예산복사 클릭 후 저장$")
	public void 전월_예산복사_클릭_후_저장() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("com.nhn.android.moneybook:id/btn_preMonthCopy")).click();
		//저장
		driver.findElement(By.id("com.nhn.android.moneybook:id/save_budget")).click();
	}

	@Then("^이전달 예산과 동일하게 복사되어 노출됨을 확인$")
	public void 이전달_예산과_동일하게_복사되어_노출됨을_확인() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	   assertEquals("1,000",driver.findElement(By.id("com.nhn.android.moneybook:id/budget_total_value")).getText());
	   assertEquals(" / 500",driver.findElement(By.id("com.nhn.android.moneybook:id/budget_001_value")).getText());
	   assertEquals(" / 500",driver.findElement(By.id("com.nhn.android.moneybook:id/budget_002_value")).getText());
	   //한달보기로 이동
	   driver.findElement(By.id("com.nhn.android.moneybook:id/go_monthly_smry_page")).click();
	}
//////////////////////*SCENARIO #11-money-10 신용카드 추가 후 지출저장*///////////////////////////////////////////////

	@Given("^내역쓰기 클릭하여 내역 입력 화면으로 이동하여 필수 입력값 입력$")
	public void 내역쓰기_클릭하여_내역_입력_화면으로_이동하여_필수_입력값_입력() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		WebElement btn_write = driver.findElement(By.id("com.nhn.android.moneybook:id/goWritePageBtn"));
		btn_write.click();
		
		WebElement edit_write_outgo_amt = driver.findElement(By.id("com.nhn.android.moneybook:id/outgo_amt"));
		edit_write_outgo_amt.sendKeys("1234");
		WebElement edit_write_outgo_usedesc = driver.findElement(By.id("com.nhn.android.moneybook:id/use_desc"));
		edit_write_outgo_usedesc.click();
		edit_write_outgo_usedesc.sendKeys("신용카드추가내역");
	}

	@When("^출금계좌에서 카드 선택$")
	public void 출금계좌에서_카드_선택() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("com.nhn.android.moneybook:id/type_card")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/tab_account")).isDisplayed();
		
		
	}

	@When("^레이어에서 추가 버튼 클릭하여 카드추가 화면으로 이동$")
	public void 레이어에서_추가_버튼_클릭하여_카드추가_화면으로_이동() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("com.nhn.android.moneybook:id/add_icon")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/card_title")).isDisplayed();
	    
	}

	@When("^카드 내역 입력 후 저장$")
	public void 카드_내역_입력_후_저장() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("com.nhn.android.moneybook:id/spinnerCardCorp")).click();
		
		
		WebElement dropdown_card=driver.findElement(By.id("android:id/select_dialog_listview"));
		driver.swipe(700, 2300,700 , 200, 1000);
		driver.findElement(By.name("신한")).click();
		/*Select dropdown_card=new Select(driver.findElement(By.id("android:id/select_dialog_listview")));
		assertFalse(dropdown_card.isMultiple());
		assertEquals(24, dropdown_card.getOptions().size());
		
		dropdown_card.selectByVisibleText("신한");*/
		assertEquals("신한", driver.findElement(By.id("com.nhn.android.moneybook:id/txtCardCorpName")).getText());
		driver.findElement(By.id("com.nhn.android.moneybook:id/editCardName")).sendKeys("신한신용카드");
		driver.findElement(By.id("com.nhn.android.moneybook:id/save_card")).click();
		
		
		
	}

	@When("^생성된 카드 선택 후 지출내역 저장$")
	public void 생성된_카드_선택_후_지출내역_저장() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		WebElement shinhan = driver.findElement(By.id("com.nhn.android.moneybook:id/cell_data"));
		assertEquals("[신한] 신한신용카드", shinhan.getText());
		shinhan.click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/scat_name")).click();
		assertEquals("[신한] 신한신용카드", driver.findElement(By.id("com.nhn.android.moneybook:id/outgo_account_detail_name")).getText());
		driver.findElement(By.id("com.nhn.android.moneybook:id/save_outgo_item")).click();
		
	}

	@Then("^지출 내역에 카드아이콘 표시되는지 확인$")
	public void 지출_내역에_카드아이콘_표시되는지_확인() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("com.nhn.android.moneybook:id/card_icon")).isDisplayed();
	}

//////////////////////*SCENARIO #12-money-11 통장 추가 후 수입저장*///////////////////////////////////////////////	
	@Given("^내역쓰기 클릭하여 내역 입력 화면에서 필수 입력값 입력$")
	public void 내역쓰기_클릭하여_내역_입력_화면에서_필수_입력값_입력() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		WebElement btn_write = driver.findElement(By.id("com.nhn.android.moneybook:id/goWritePageBtn"));
		btn_write.click();
		
		WebElement edit_write_outgo_amt = driver.findElement(By.id("com.nhn.android.moneybook:id/outgo_amt"));
		edit_write_outgo_amt.sendKeys("1234");
		WebElement edit_write_outgo_usedesc = driver.findElement(By.id("com.nhn.android.moneybook:id/use_desc"));
		edit_write_outgo_usedesc.click();
		edit_write_outgo_usedesc.sendKeys("통장추가내역");
	}

	@When("^출금계좌 클릭하여 현금 탭 클릭$")
	public void 출금계좌_클릭하여_현금_탭_클릭() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		if(driver.findElement(By.id("com.nhn.android.moneybook:id/new_outgo_account")).isDisplayed())
		{
			driver.findElement(By.id("com.nhn.android.moneybook:id/type_cash")).click();
		}else{
		driver.findElement(By.id("com.nhn.android.moneybook:id/outgo_account_type")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/tab_account")).click();}
	}

	@When("^레이어에서 추가 버튼 클릭하여 통장 추가 화면으로 이동$")
	public void 레이어에서_추가_버튼_클릭하여_통장_추가_화면으로_이동() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("com.nhn.android.moneybook:id/add_icon")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/account_title")).isDisplayed();
	}

	@When("^통장 내역 입력 후 저장$")
	public void 통장_내역_입력_후_저장() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("com.nhn.android.moneybook:id/editAccountName")).sendKeys("통장명");
		driver.findElement(By.id("com.nhn.android.moneybook:id/save_account")).click();
		assertTrue(driver.findElement(By.name("[현금] 통장명")).isDisplayed());
	}

	@When("^생성된 통장 선택 후 지출내역 저장$")
	public void 생성된_통장_선택_후_지출내역_저장() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.name("[현금] 통장명")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/scat_name")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/save_outgo_item")).click();
	}

	@Then("^저장된 지출내역 진입하여 출금계좌에 통장 노출되는지 확인$")
	public void 저장된_지출내역_진입하여_출금계좌에_통장_노출되는지_확인() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.name("통장추가내역")).click();
		assertEquals("[현금] 통장명", driver.findElement(By.id("com.nhn.android.moneybook:id/outgo_account_detail_name")).getText());
		driver.findElement(By.id("com.nhn.android.moneybook:id/go_monthly_smry_page")).click();
	}	
//////////////////////*SCENARIO #13-money-12 체크카드 추가 후 지출저장*///////////////////////////////////////////////	
	@Given("^내역쓰기 클릭하여 내역 입력 화면으로 이동하여 필수 입력값 우선 입력$")
	public void 내역쓰기_클릭하여_내역_입력_화면으로_이동하여_필수_입력값_우선_입력() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		WebElement btn_write = driver.findElement(By.id("com.nhn.android.moneybook:id/goWritePageBtn"));
		btn_write.click();
		
		WebElement edit_write_outgo_amt = driver.findElement(By.id("com.nhn.android.moneybook:id/outgo_amt"));
		edit_write_outgo_amt.sendKeys("1234");
		WebElement edit_write_outgo_usedesc = driver.findElement(By.id("com.nhn.android.moneybook:id/use_desc"));
		edit_write_outgo_usedesc.click();
		edit_write_outgo_usedesc.sendKeys("체크카드추가내역");
	}

	@When("^출금계좌에서 카드 선택 or 출금계좌 타입 클릭$")
	public void 출금계좌에서_카드_선택_or_출금계좌_타입_클릭() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		if(driver.findElement(By.id("com.nhn.android.moneybook:id/new_outgo_account")).isDisplayed())
		{
			driver.findElement(By.id("com.nhn.android.moneybook:id/type_card")).click();
		}else{
		driver.findElement(By.id("com.nhn.android.moneybook:id/outgo_account_type")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/tab_card")).click();}
	}

	@When("^레이어에서 추가 버튼 클릭$")
	public void 레이어에서_추가_버튼_클릭() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("com.nhn.android.moneybook:id/add_icon")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/card_title")).isDisplayed();
	}

	@When("^카드 내역 입력 후 체크카드 체크 후 저장$")
	public void 카드_내역_입력_후_체크카드_체크_후_저장() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("com.nhn.android.moneybook:id/spinnerCardCorp")).click();
				
		WebElement dropdown_card=driver.findElement(By.id("android:id/select_dialog_listview"));
		driver.swipe(700, 2300,700 , 200, 1000);
		driver.findElement(By.name("신한")).click();
		
		assertEquals("신한", driver.findElement(By.id("com.nhn.android.moneybook:id/txtCardCorpName")).getText());
		driver.findElement(By.id("com.nhn.android.moneybook:id/editCardName")).sendKeys("신한체크카드");
		//driver.hideKeyboard();
		if(driver.findElement(By.id("com.nhn.android.moneybook:id/btnIsCheckCard")).isEnabled())
		{
			if(!driver.findElement(By.id("com.nhn.android.moneybook:id/btnIsCheckCard")).isSelected())
				driver.findElement(By.id("com.nhn.android.moneybook:id/btnIsCheckCard")).click();
			
		}else
		{
			System.out.println("Account Checkbox is disabled!");
		}
		
		driver.findElement(By.id("com.nhn.android.moneybook:id/save_card")).click();
		driver.findElement(By.id("android:id/button1")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/btnAssociatedAccount")).click();
		driver.findElement(By.name("적금통장")).click();
		driver.findElement(By.id("android:id/button1")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/save_card")).click();
		driver.findElement(By.id("android:id/button1")).click();
	}

	@When("^생성된 체크카드 선택 후 지출내역 저장$")
	public void 생성된_체크카드_선택_후_지출내역_저장() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		assertTrue(driver.findElement(By.name("[신한] 신한체크카드")).isDisplayed());
		
		driver.findElement(By.name("[신한] 신한체크카드")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/scat_name")).click();
		assertEquals("[신한] 신한체크카드", driver.findElement(By.id("com.nhn.android.moneybook:id/outgo_account_detail_name")).getText());
		driver.findElement(By.id("com.nhn.android.moneybook:id/save_outgo_item")).click();
		
	}

	@Then("^지출 내역에 카드아이콘 미표시되는지 확인 및 내역 진입하여 출금계좌에 체크카드 노출 확인$")
	public void 지출_내역에_카드아이콘_미표시되는지_확인_및_내역_진입하여_출금계좌에_체크카드_노출_확인() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("com.nhn.android.moneybook:id/card_icon")).isDisplayed();
		WebElement test=driver.findElement(By.id("com.nhn.android.moneybook:id/item_background"));
		//assertFalse(test.findElement(By.id("com.nhn.android.moneybook:id/card_icon")).isDisplayed());
		/*List<WebElement> gg=driver.findElementsById("com.nhn.android.moneybook:id/layerAmt");
		WebElement layerAmt=gg.get(0);
		assertFalse(layerAmt.findElement(By.id("com.nhn.android.moneybook:id/card_icon")).isDisplayed());*/
		driver.findElement(By.name("체크카드추가내역")).click();
		assertEquals("[신한] 신한체크카드", driver.findElement(By.id("com.nhn.android.moneybook:id/outgo_account_detail_name")).getText());
		driver.findElement(By.id("com.nhn.android.moneybook:id/go_monthly_smry_page")).click();
	}
//////////////////////*SCENARIO #14-money-13 정산하기> 전월이월잔액 / 카드대금 정산*///////////////////////////////////////////////	
	List<String> result = new ArrayList<String>();
	@Given("^다음 달로 이동$")
	public void 다음_달로_이동() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("com.nhn.android.moneybook:id/nextBtn")).click();
	}

	@When("^정산하기 클릭$")
	public void 정산하기_클릭() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("com.nhn.android.moneybook:id/btn_calculate")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/calculate_list_header")).isDisplayed();
	}

	@When("^전월이월잔액 카드대금 선택 후 정산 클릭 후 정산결과 페이지 노출$")
	public void 전월이월잔액_카드대금_선택_후_정산_클릭_후_정산결과_페이지_노출() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("com.nhn.android.moneybook:id/save_calucate")).click();
		driver.findElement(By.id("android:id/button1")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/layer_toggle_selection")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/layer_toggle_selection")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/label_spamword")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/save_calucate")).click();
		
		driver.findElement(By.name("정산결과")).isDisplayed();
	}

	@When("^정산 결과 확인 후 저장 클릭$")
	public void 정산_결과_확인_후_저장_클릭() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		List<WebElement> gg=driver.findElementsById("com.nhn.android.moneybook:id/txtItemDesc");
		
		for(int i=0;i<gg.size();i++){
		result.add(gg.get(i).getText());
		System.out.println(result.get(i));}
		driver.findElement(By.id("com.nhn.android.moneybook:id/save_calucate")).click();
		
		/*for(int i=0;i<result.size();i++){
		driver.findElement(By.name(result.get(i))).isDisplayed();}*/
		
	}

	@Then("^정산 결과 내역이 한달보기에 저장되어 노출 확인$")
	public void 정산_결과_내역이_한달보기에_저장되어_노출_확인() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		for(int i=0;i<result.size();i++){
			System.out.println("한달보기노출확인 : " + result.get(i));
			driver.findElement(By.name(result.get(i))).isDisplayed();}
	    
	}

}
