(function() {
	"use strict";

	/**
	 * Vraća funkciju koju ćemo koristiti kao handler za klik na <li> elementima.
	 */
	function getClickHandler(repo) {
		return function() {
			alert(repo.description);
		};
	}

	/**
	 * Prikazuje listu repozitorija na stranici prikazujući svaki repozitorij objekat
	 * kao <li> element.
	 */
	function displayRepos(repos) {
		// nalazimo element koji predstavlja <ol> u kojem ćemo prikazivati nazive repozitorija
		// (rezultat je jQuery objekat), te mu postavljamo opacity na nulu da ga učinimo nevidljivim
		var reposList = $('#repo-list');
		reposList.css({opacity: 0});

		// petlja koja prolazi kroz sve elemente niza `repos` (niz repozitorij objekata)
		for (var i = 0; i < repos.length; i++) {
			// pravimo <li> element kojem kao tekst dajemo naziv repozitorija. Rezultat je jQuery
			// objekat kojem dajemo `click` handler koristeći rezultat izvršavanja funkcije `getClickHandler()`.
			// Nakon toga <li> stavljamo u listu (<ol>).
			var listItem = $('<li>', { text: repos[i].name });
			listItem.on('click', getClickHandler(repos[i]));
			listItem.appendTo(reposList);
		}

		// koristimo jQuery da animiramo opacity do vrijednosti 1.0 kroz dvije sekunde
		reposList.animate({ opacity: 1.0 }, 2000);
	}

	/**
	 * Funkcija koja dohvaća repozitorije za zadati username.
	 *
	 * Funkcija ne vraća rezultat kao return value, već poziva callback funkciju tek u onom momentu
	 * kada server pošalje response.
	 */
	function fetchReposForUser(username, callback) {
		$.getJSON("https://api.github.com/users/" + username + "/repos", function(repos, status, jqXHR) {
			callback(repos);
		});
	}

	// kreiramo handler za `click` event na dugme koji će dohvatiti repozitorije s GitHub API-ja,
	// primiti te rezultate u callback funkciji, te onda pozvati funkciju `displayRepos()` da
	// prikaže repozitorije na stranici
	$('#fetch-button').on('click', function() {
		var username = $('#username').val();

		fetchReposForUser(username, function(repos) {
			displayRepos(repos);
		});
	});
}());
