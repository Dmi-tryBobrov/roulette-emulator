# roulette-emulator
RU:
Файл roulette_1_00.jar является исполняемым и поддерживает полную функциональность приложения (проверено на Ubuntu 20.04 LTS и Windows 10), в качетве SDK использовалась
Java 17 LTS (https://www.oracle.com/java/technologies/downloads/)
Specification: JSR- 392 Java SE 17 ("Specification"); Version: 17; Status: Final Release; Release: September 2021.

EN:
This application allows you to emulate the martingale roulette game (betting strategy in which the gambler double the bet after every loss,
so that the first win would recover all previous losses plus win a profit equal to the original stake).
In order to start an emulation, enter the required values in the appropriate fields.
You have to provide available amount of money (obviously you can't spend more money playing roulette than you have) and your first bet.
Then you should specify a sum with which you will be willing to left "the table". Otherwise a game could potentially last forever.

You can choose between two roulette styles - American (18 Reds + 18 Blacks + 2 "0-es", total 38 pockets)
and European (18 Reds + 18 Blacks + 1 "0", total 37 pockets).
In order to do so just press the corresponding button. European style is selected by defaut, so don't press anything if you prefer this one.
Interaction is performed via GUI, you can use a mouse or press Tab key to switch between clickable (buttons) and editable (entry fields) elements.
You can access menu items using hotkeys, Alt+F for File, and Alt+H for help. Navigation can be performed 
Current version doesn't support maximum bet limitation, this option is coming soon.

Current version only displays first first 100 rolls and then the last 100 rolls of roulette in listing window.
This is hardcoded, changing the number of rolls to display is not supported as of yet.

After you've entered a value in a field, press corresponding submit button.
Pressed button will become red and simulation starts as soon as all three buttons have been pressed.

You can repeat simulation with the last parameters by simply clicking "Retry" button. This button always repeats calculation with the last entered values.

You can cancel long calculations by pressing "Cancel" button, then you can either retry with the last parameters or enter the new ones.
Lengthy emulation doesn't freeze GUI cause they are executed in the background thread (not in EDT).

A detailed report (limited to first and last 100 rolls) with all recorded steps can be obtained by clicking on a "Listing" button.
You can export the last report in .txt format in File -> Save menu.

Pay attention when you save the last output in a .txt file. Current version DOES notify about file overwriting, but still data loss is possible if you are not careful.
You can provide a full name with extention .txt -> "output.txt" for example, or just enter only file name -> "output". "listing.txt" for name is suggested by default.
Saving in other formats (.pdf) is not supported, althouh .doc and .docx extensions is possible, but not guaranted.
Application supports input check (not a number, float or negative, whether you have not enough money to make a bet or your sum is more then you set as desired), in case 
of error emulation does not start, a pop-up menu displays and suggests a correction.

RU:
Эмулятор игры в рулетку (европейский и американский вариант) методом мартингейла (удвоение предыдущей ставки каждый проигранный раунд,
что позволяет возместить все потерянные в ряду неудач деньги и получить прибыль в размере начальной ставки).

Взаимодоействие осуществляется через GUI, навигацию по интерактивным элементам (кнопки и поля ввода) можно осуществлять нажатием Tab, 
подтверждение - Enter, элементы меню доступны через горячие клавиши (Alt + F, Alt + H - нужная буква подчеркнута на панели.
Также ввод можно осуществлять с помощью мыши.
Можно вводить различные варианты начальной ставки, начальной суммы и суммы окончания игры в рулетку.
После набора данных в поле ввода необходимо нажать кнопку подтверждения, после подтверждения всех данных расчет начинается автоматически.
В зависимости от этих параметров проиграть либо очень легко, либо очень сложно, что можно увидеть, запустив симуляцию.

Приложение подерживает вывод последней игры (поскольку читать бесконечный список ходов не интересно, выводятся первые 100 и последние 100 ходов).
В данный момент данное ограничение изменить в настройках нельзя.
Длительные расчеты НЕ влияют на отзывчивость GUI, т.к. идут в отдельном треде (background thread).
Имеется возможность остановить текущую эмуляцию, а также повторить эмуляцию с предыдущими параметрами неограниченное число раз.
Последний результат можно сохранить в формате .txt под любым именем, по умолчанию предлагается название "listing.txt".
Поддерживается проверка перезаписи уже существующих файлов.
В меню доступна краткая справка (приложение и справка на английском языке).
В случае ввода неверных параметров (не число, дробное или отрицательное значение, ставка превышает доступную сумму или начальная сумма уже больше
желаемого выигрыша) - выводится сообщение с описанием ситуации и действий по исправлению, расчет не начинается.
