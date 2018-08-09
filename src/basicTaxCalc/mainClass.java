package basicTaxCalc;

import java.text.DecimalFormat;
import java.util.Scanner;

public class mainClass {
	private static double income;
	private static double payrate;
	private static double hours = 2000;
	private static double netIncome;
	private static double stateTaxedpay;
	private static double federalTaxedpay;
	private static double medicareTaxedPay;
	private static double socialSecTaxedPay;
	//Decimal formatter
	final private static DecimalFormat df = new DecimalFormat("#0.00");
	final private static String pad = "               ";
	//Tax brackets
	final private static double stateFlatTax = .05499;
	final private static double medicareTax = .062;
	final private static double socialSecTax = .0145;
	final private static double[] fedTaxBrackets = {.1,.12,.22,.24,.32,.35,.37};
	final private static double[] fedTaxBracketM = {0,9525,38700,83500,157500,200000,500000,0};
	/**
	 * 
	 * @param args TODO find out what this does
	 */
	public static void main( String args[]) {
		System.out.println("2017 Tax estimator! (Which assumes you work 2000 hours a year)");
		System.out.println("This program does not account for any deductibles.");
		System.out.print("Enter the hourly pay rate: ");
		Scanner scan = new Scanner(System.in);
		payrate = scan.nextDouble();
		//System.out.print("Enter hours worked: ");
		//hours = scan.nextDouble();
		scan.close();
		income = payrate * hours;	
		taxCalc();
	}
	
	private static void taxCalc() {		
		double[] incomePerBracket = new double[7];
		double tempStt;
		double tempFed;
		double tempMed;
		double tempSS;
		printDesign1();
		System.out.println(pad + beforePadding("IncomeInBracket") + beforePadding("Fed Tax") + beforePadding("State Tax") + beforePadding("Medicare") + beforePadding("SocialSec"));
		for (int i = 0; i < 7; i++) {
			incomePerBracket[i] = calcBracket(income, fedTaxBracketM[i], fedTaxBracketM[i+1]);
			if (incomePerBracket[i] > 0) {
				tempStt = incomePerBracket[i] * stateFlatTax;
				tempFed = incomePerBracket[i] * fedTaxBrackets[i];
				tempMed = incomePerBracket[i] *  medicareTax;
				tempSS = incomePerBracket[i] * socialSecTax;
				System.out.println("Bracket"+(i+1)+":      " + doubleConvert(incomePerBracket[i]) + doubleConvert(tempStt) + doubleConvert(tempFed)
				 												+ doubleConvert(tempMed) + doubleConvert(tempSS));
				stateTaxedpay = stateTaxedpay + tempStt;
				federalTaxedpay = federalTaxedpay + tempFed;
				medicareTaxedPay = medicareTaxedPay + tempMed;
				socialSecTaxedPay = socialSecTaxedPay + tempSS;
			}
			else { i = 7; } //EXIT
		}
		printDesign2();
//		System.out.println(afterPadding("Gross income: ") + doubleConvert(income));
//		System.out.println(afterPadding("State taxes: ") + doubleConvert(stateTaxedpay));
//		System.out.println(afterPadding("Federal taxes: ") + doubleConvert(federalTaxedpay));
//		System.out.println(afterPadding("Medicare: ") + doubleConvert(medicareTaxedPay));
//		System.out.println(afterPadding("Social Security: ") + doubleConvert(socialSecTaxedPay));
		netIncome = income - stateTaxedpay - federalTaxedpay - medicareTaxedPay - socialSecTaxedPay;
		System.out.println(afterPadding("Net income: ") + doubleConvert(netIncome));
		System.out.println(afterPadding("Month income: ") + doubleConvert((netIncome) / 12));
		System.out.println(afterPadding("BiWeek income: ") + doubleConvert((netIncome) / 24));
		
		
	}
	
		private static double calcBracket(double pay, double lowerBound, double upperBound) {
		if (pay > lowerBound) {
			if (pay > upperBound) {
				if (upperBound == 0) {
					return pay - lowerBound;
				}
				return upperBound - lowerBound;
			}
			return pay - lowerBound;
		}
		return 0;
	}
		
	private static String doubleConvert(double val) {
		//String tempVal = "$" + df.format(val);
		//tempVal = (pad.substring(0, 15 - tempVal.length()) + tempVal);
		return beforePadding("$" + df.format(val));
	}
	/**
	 * Adds up to 15 blank spaces to the back of a string to give it the length of 15.
	 * @param str
	 * @return
	 */
	private static String afterPadding(String str) {
		//If over length
		if (str.length() > 15) {
			return str.subSequence(0,11) + "...:";
		}
		//Else
		return str + pad.substring(0, 15 - str.length());
	}
	/**
	 * Adds up to 15 blank spaces to the front of a string to give it the length 15.
	 * @param str
	 * @return
	 */
	private static String beforePadding(String str) {
		//If over length
		if (str.length() > 15) {
			return "..." + str.subSequence(0,12);
		}
		//Else
		return pad.substring(0, 15 - str.length()) + str;
	}
	
	private static void printDesign1() {
		System.out.println("------------------------------------------------------------------------------------------");
		System.out.println("--                                   Charles T. Kirby                                   --");
		System.out.println("------------------------------------------------------------------------------------------");
	}
	
	private static void printDesign2() {
		System.out.println("------------------------------------------------------------------------------------------");
		System.out.println(afterPadding("Totals:") + doubleConvert(income) + doubleConvert(federalTaxedpay) + doubleConvert(stateTaxedpay) + doubleConvert(medicareTaxedPay) + doubleConvert(socialSecTaxedPay));
		System.out.println("------------------------------------------------------------------------------------------");
	}
}
