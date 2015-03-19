(function() {
	"use strict";

	/**
	 * Konstruktor funkcija koja prima dva parametra i dodjeljuje njihove vrijednosti
	 * istoimenim atributima objekta koji se konstruiše
	 */
	function Person (firstName, lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * Mijenjamo prototip konstruktora.
	 *
	 * Sve funkcije koje definišemo na prototipu će biti dostupne kao metode na objektima
	 * koje konstruišemo koristeći konstruktor `Person`.
	 */
	Person.prototype = {

		getFullName: function() {
			return this.firstName + " " + this.lastName;
		}

	};

	// Sada možemo napraviti objekte koristeći Person konstruktor i pozivati
	// metodu `getFullName()` na njima
	var fata = new Person("Fata", "Patić");
	console.log(fata.getFullName());

	var haso = new Person("Haso", "Fusić");
	console.log(haso.getFullName());

}());
