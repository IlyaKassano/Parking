window.onload = function() {
	let elem = document.getElementsByClassName("cost");

	for (let i = 0; i < elem.length; i++) {
		if(elem[i].innerHTML == "")
			elem[i].innerHTML = "0";
		else
			elem[i].innerHTML = parseFloat(elem[i].innerHTML).toFixed(2);
	}
}