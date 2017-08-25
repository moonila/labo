function viewOrHideComponent(hideElementId, showElementId) {

	var hideElement = document.getElementById(hideElementId);
	var showElement = document.getElementById(showElementId);

	hideElement.style.display = 'none';
	showElement.style.display = 'block';

}

function viewPopUp(popUpId, fadeElementId){
	var showElement = document.getElementById(popUpId);
	showElement.style.display = 'block';
	
	var fadeElement = document.getElementById(fadeElementId);
	fadeElement.style.display = 'block';
}

function hidePopUp(popUpId){
	var hideElement = document.getElementById(popUpId);
	hideElement.style.display = 'none';
}

function formValidation(formName, formElementId,errorMessage) {

	var formNameElementValue= document.forms.form4.newWordsId.value;
	alert(formNameElementValue);
	var elementRef = document.getElementById(errorMessage);

	if (formNameElementValue == "") {
		elementRef.style.display = 'block';
		return false;
	} else {
		elementRef.style.display = 'none';
		return true;
	}
}