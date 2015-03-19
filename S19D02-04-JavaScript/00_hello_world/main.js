/**
 * Kbacuje novi <h1> element u stranicu s tekstom "Hi {name}!"
 */
function sayHi(name) {
	var greeting = document.createElement("h1");
	greeting.innerText = "Hi " + name + "!";

	var body = document.getElementsByTagName("body")[0];
	body.appendChild(greeting);
}

// definišemo varijablu i prosljeđujemo vrijednost varijable name kao argument funkciji
var name = "Gorjan";
sayHi(name);

// definišemo varijablu u koju stavljamo funkciju i onda pozivamo funkciju koristeći novu varijablu
var f = sayHi;
f("Selma");
