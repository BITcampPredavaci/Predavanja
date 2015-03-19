(function() {
	"use strict";

	/**
	 * Animira opacity na elementu koji je proslijeđen kao prvi argument.
	 */
	function animate(element) {
		// definišemo interval koji će izvršavati anonimnu funkciju (prvia argument)
		// svakih 17 milisekundi (~60 frames per second)
		var interval = setInterval(function() {
			// čitamo prethodni opacity i pretvaramo ga u broj
			var opacity = Number(element.style.opacity);
			// povećavamo opacity za 0.01
			element.style.opacity = opacity + 0.01;

			// ako je opacity veći ili jednak jedinici, prekidamo interval (tj.
			// ova anonimna funkcija više neće biti pozivana svakih 17 milisekundi)
			if (opacity >= 1) {
				clearInterval(interval);
			}
		}, 17);
	}

	/**
	 * Vraća funkciju koju ćemo koristiti kao handler za klik na <li> elementima.
	 */
	function getClickHandler(repo) {
		return function() {
			alert(repo.description);
		};
	}

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

		// postavljamo opacity na 0 da učinimo listu providnom, tj. nevidljivom
		reposList.style.opacity = 0;

		// petlja koja prolazi kroz sve elemente niza `repos` (niz repozitorij objekata)
		for (var i = 0; i < repos.length; i++) {

			// pravimo <li> element kojem kao tekst dajemo naziv repozitorija
			var listItem = document.createElement('li');
			listItem.innerText = repos[i].name;

			// kao handler za `click` event dodjeljujemo funkciju koju vraća getClickHandler funkcija
			listItem.onclick = getClickHandler(repos[i]);

			// dodajemo kreirani <li> u <ol id="repo-list">
			reposList.appendChild(listItem);
		}

		// animiramo opacity <ol id="repo-list"> elementa tako da postane lagano vidljiv
		animate(reposList);
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
