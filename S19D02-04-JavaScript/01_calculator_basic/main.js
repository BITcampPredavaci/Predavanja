// Koristimo DOM API da pronađemo dugme s ID-em `calculate` u HTML-u
var button = document.getElementById('calculate');

// Dajemo dugmetu handler za event 'click' tako što anonimnu funkciju
// dodijelimo atributu 'onclick' proizvoljnog DOM elementa
button.onclick = function() {
	var op1 = document.getElementById('operand1');
	var op2 = document.getElementById('operand2');
	var result = document.getElementById('result');
	var operator = document.getElementById('operator');

	// pretvaramo vrijednost iz <input> elementa u broj koristeći parseFloat
	var op1Value = parseFloat(op1.value);
	var op2Value = parseFloat(op2.value);

	// validiramo vrijednosti. ako neka od vrijednosti nije broj, dajemo <input> polju
	// crveni okvir
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

	// vršimo odabranu računsku operaciju, koristeći `value` atribut DOM objekta
	// koji predstavlja <select> element. Ovaj atribut će imati vrijednost koja
	// je postavljena u `value` HTML atributu trenutno odabranog `<option>`-a
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
}
