// IIFE: Immediately-Invoked Function Expression
(function() {
	"use strict";
	
	/**
	 * Računa rezultat na osnovu vrijednosti u poljima i odabranog operatora.
	 *
	 * Funkciju možemo koristiti kao event handler za proizvoljni element/event.
	 */
	function calculationHandler() {
		var op1 = document.getElementById('operand1');
		var op2 = document.getElementById('operand2');
		var result = document.getElementById('result');
		var operator = document.getElementById('operator');

		var op1Value = parseFloat(op1.value);
		var op2Value = parseFloat(op2.value);

		if (Number.isNaN(op1Value)) {
			op1.className = "error";
		} else {
			op1.className = op1.className.replace(/error/, '');
		}

		if (Number.isNaN(op2Value)) {
			op2.className = "error";
		} else {
			op2.className = op2.className.replace(/error/, '');
		}

		switch (operator.value) {
		case '+':
			result.value = op1Value + op2Value;
			break;

		case '-':
			result.value = op1Value - op2Value;
			break;

		case '*':
			result.value = op1Value * op2Value;
			break;

		case '/':
			result.value = op1Value / op2Value;
			break;
		}
	};

	// pronalazimo sve elemente
	var op1 = document.getElementById('operand1');
	var op2 = document.getElementById('operand2');
	var operator = document.getElementById('operator');

	// ako se polja za bilo koji operand promijene, treba promijeniti rezultat.
	// Zato ćemo dodijeliti calculationHandler svim relevantnim event-ima.
	op1.onchange = calculationHandler;
	op1.onkeyup = calculationHandler;
	op2.onchange = calculationHandler;
	op2.onkeyup = calculationHandler;
	operator.onchange = calculationHandler;
}());
