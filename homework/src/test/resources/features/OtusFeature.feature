
Feature: Otus

# -Demail=eueiam@mailinator.com -Dpassword=Test1111

  Background: To Launch the browser
    Given Launch the browser

  @Test
  Scenario: Login Otus and check user name
    When Open Otus on your browser
    Then Login to Otus
    And Check user name is "Тест" after login
    Then Shut down browser

  @Test
  Scenario: Fill Otus personal data
    When Open Otus on your browser
    Then Login to Otus
    And Go to personal data page
    And Fill profile by personal data
    And Check "Данные успешно сохранены" is showing
    Then Shut down browser

  @Test
  Scenario: Check Otus email
    When Open Otus on your browser
    Then Check Otus support email is "help@otus.ru"
    Then Shut down browser

  @Test
  Scenario: Check Otus phone
    When Open Otus on your browser
    Then Check Otus phone is "+7 499 938-92-02"
    Then Shut down browser

  @Test
  Scenario: Check Otus Vk Link
    When Open Otus on your browser
    Then Go to Otus Contact Page
    And Check "Вконтакте" link is "https://vk.com/club145052891"
    Then Shut down browser

  @Test
  Scenario: Check Otus FB Link
    When Open Otus on your browser
    Then Go to Otus Contact Page
    And Check "Facebook" link is "https://www.facebook.com/otus.ru/"
    Then Shut down browser

  @Test
  Scenario: Check Otus OK Link
    When Open Otus on your browser
    Then Go to Otus Contact Page
    And Check "OK" link is "https://ok.ru/group/54448251797611"
    Then Shut down browser

  @Test
  Scenario: Check Otus OK Link
    When Open Otus on your browser
    Then Go to Otus Contact Page
    And Check "YouTube" link is "https://www.youtube.com/channel/UCetgtvy93o3i3CvyGXKFU3g"
    Then Shut down browser

  @Test
  Scenario: Check Otus Yandex Zen Link
    When Open Otus on your browser
    Then Go to Otus Contact Page
    And Check "Yandex Zen" link is "https://zen.yandex.ru/id/5bbcbc1ba5bd5400a990e7d9"
    Then Shut down browser

  @Test
  Scenario: Check Otus Title
    When Open Otus on your browser
    Then Check Otus Title is "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям"
    Then Shut down browser