(function() {
	"use strict";

	// pronalazimo link koji treba otvoriti modalni dijalog
	var loginLink = document.getElementById('loginLink');

	// linku dodajemo handler za `click` event koji prikazuje modalni dijalog za login
	loginLink.onclick = function(e) {
		e.preventDefault();

		// prikazujemo sakriveni <div>
		var loginForm = document.getElementById('loginForm');
		loginForm.style.display = 'block';

		// kreiramo overlay <div> (stiliziran kroz CSS)
		var overlay = document.createElement('div');
		overlay.className = 'overlay';

		// dodajemo overlay kao dijete <body> elementu
		var body = document.querySelector('body');
		body.appendChild(overlay);
	};

	/**
	 * Zatvara modalni dijalog
	 *
	 * Podrazumijeva se da će se ova funkcija koristiti kao event handler, tj. da
	 * će u momentu izvršavanja funkcije referenca `this` pokazivati na DOM objekat
	 * koji predstavlja dugme za zatvaranje modala koje je direktno ugniježdeno
	 * u modal dijalog <div>.
	 */
	function closeModal() {
		var modal = this.parentElement;
		modal.style.display = 'none';

		var overlay = document.querySelector('.overlay');
		overlay.parentElement.removeChild(overlay);
	};

	// pronalazimo sva dugmad s klasom `close-modal` i dajemo im funkciju `closeModal()` kao event handler
	var closeModalButtons = document.querySelectorAll('.close-modal');

	for (var i = 0; i < closeModalButtons.length; i++) {
		closeModalButtons[i].onclick = closeModal;
	}
}());
