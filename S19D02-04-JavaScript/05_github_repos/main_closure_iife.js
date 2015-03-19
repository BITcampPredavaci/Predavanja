(function() {
	"use strict";

	// kreiramo XML HTTP Request objekat
	var xhr = new XMLHttpRequest();

	// dodajemo handler za `load` event. Handler će biti pozvan kada stigne response od servera
	xhr.onload = function() {
		// this.responseText predstavlja tijelo odgovora servera. U ovom slučaju je to JSON string.
		// Koristimo `JSON.parse()` da pretvorimo JSON string u JS objekte i dodjeljujemo niz objekata
		// varijabli `repos`
		var repos = JSON.parse(this.responseText);

		// nalazimo DOM objekat koji predstavlja <ol> u kojem ćemo prikazivati nazive repozitorija
		var reposList = document.querySelector('#repo-list');

		// petlja koja prolazi kroz sve elemente niza `repos` (niz repozitorij objekata)
		for (var i = 0; i < repos.length; i++) {

			// pravimo <li> element kojem kao tekst dajemo naziv repozitorija
			var listItem = document.createElement('li');
			listItem.innerText = repos[i].name;

			// koristime IIFE da napravimo novi scope za varijablu `repo` (parametar IIFE-a).
			// Event handler će napraviti closure oko ove varijable.
			//
			// Na ovaj način izbjegavamo pravljenje closure-a oko varijable `i` što dovodi do bugova.
			// Isti problem se može riješiti i pisanjem funkcije koja vraća funkciju (pogledajte
			// main_final_plain_js.js ili main.js).
			(function(repo) {
				listItem.onclick = function() {
					alert(repo.description);
				};
			}(repos[i]));

			// dodajemo kreirani <li> u <ol id="repo-list">
			reposList.appendChild(listItem);
		}
	};

	// dodajemo handler za `error` event. Handler će biti pozvan ako dođe do greške prilikom
	// dobavljanja podataka
	xhr.onerror = function() {
		alert("Nema podataka :(");
	}

	// otvaramo konekciju i šaljemo request
	xhr.open("GET", "https://api.github.com/users/sanella/repos");
	xhr.send();
}());
