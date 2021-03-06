# roulette-emulator

<h1> General </h1>

* This application allows you to emulate the martingale (see below) roulette game. In order to start a simulation, enter the required values in the appropriate fields.
You have to provide available amount of money (obviously you can't spend more money playing roulette than you have) and your first bet.
Also you should specify a sum with which you will be willing to left "the table". Otherwise a game could potentially last forever.

* You can choose between two roulette styles - American (18 Reds + 18 Blacks + 2 "0-es", total 38 pockets) and European (18 Reds + 18 Blacks + 1 "0", total 37 pockets).
In order to do so just press the corresponding button. European style is selected by defaut, so don't press anything if you prefer this one.

* Current version doesn't support maximum bet limitation, this option is coming soon.

* Current version only displays first 100 rolls a then the last 100 rolls of roulette in listing window.
This is hardcoded, changing number of rolls to display is coming soon.

<h1> Controls </h1>

* After you've entered a value in a field, press corresponding submit button.
Pressed button will become red and simulation starts as soon as all three buttons have been pressed.

* You can repeat simulation with the last parameters by simply clicking "Retry" button. This button always repeats calculation with the last entered values.

* You can cancel long calculations by pressing "Cancel" button, then you can either retry with the last parameters or enter the new ones.

* A detailed report (limited to first and last 100 rolls) with all recorded steps can be obtained by clicking on a "Listing" button.
You can export the last report in .txt format in File -> Save menu.

* You can access menu items using hotkeys, Alt+F for File, and Alt+H for help.

<h1> WARNING </h1>

Pay attention when you save the last output in a .txt file. Current version DOES notify about file overwriting, but still data loss is possible if you are not careful.
You can provide a full name with extention .txt -> "output.txt" for example, or just enter only file name -> "output".
Saving in other formats (.pdf) is not supported, althouh .doc and .docx extensions is possible, but not guaranted.

<h1> MARTINGALE INFO </h1>

A martingale is a class of betting strategies that originated from and were popular in 18th-century France. The strategy had the gambler double the bet after every loss,
so that the first win would recover all previous losses plus win a profit equal to the original stake. Since a gambler will almost surely eventually flip heads, the martingale
betting strategy is certain to make money for the gambler provided they have infinite wealth and there is no limit on money earned in a single bet. However, no gambler has infinite
wealth, and the exponential growth of the bets can bankrupt unlucky gamblers who chose to use the martingale, causing a catastrophic loss. Despite the fact that the gambler usually wins
a small net reward, thus appearing to have a sound strategy, the gambler's expected value remains zero because the small probability that the gambler will suffer a catastrophic loss exactly
balances with the expected gain. In a casino, the expected value is negative, due to the house's edge. Additionally, as the likelihood of a string of consecutive losses occurs more often than
common intuition suggests, martingale strategies can bankrupt a gambler quickly. The martingale strategy has also been applied to roulette, as the probability of hitting either red or black is
close to 50%.
