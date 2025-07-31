function loadDataEdit(element) {

	const employeeId = element.dataset.id;
	
	const trEmployee = element.closest('tr');

	const employeeEnabled = element.dataset.enabled === 'true';		
	const employeeName = trEmployee.children[1].textContent.trim();
	const employeeSurname = trEmployee.children[2].textContent.trim();
	const employeeDepartment = element.dataset.iddept;
	
	document.getElementById('activeEmpMore').checked = employeeEnabled;
	document.getElementById("nameEmpMore").value = employeeName;
	document.getElementById("surnameEmpMore").value = employeeSurname;
	document.getElementById("departmentEmpMore").value = employeeDepartment;

	document.getElementById("idEmpMore").value = employeeId;

	const form = document.getElementById('formEditEmpMore');
	form.action = "/timeTracking/employees/more/" + employeeId + "/edit";

	showErrorsModal();
}

function showErrorsModal(errors = {}) {

	document.querySelectorAll(".alert-danger").forEach(alert => {
		alert.innerHTML = "";
		alert.style.display = "none";
		alert.classList.add("d-none");
	});

	for (const field in errors) {
		const alertDiv = document.getElementById(`error-${field}`);
		if (alertDiv) {
			alertDiv.innerHTML = errors[field].join("<br>");
			alertDiv.style.display = "block";
			alertDiv.classList.remove("d-none");
		}
	}

}

function reloadListEmployees(e) {

	e.preventDefault();

	const form = document.getElementById("formEditEmpMore");
	const url = form.action;
	const formData = new FormData(form);
	const id = document.getElementById("idEmpMore").value;

	fetch(url, {
		method: "POST",
		body: formData,
		headers: {
			"X-Requested-With": "XMLHttpRequest"
		}
	})
		.then(response => {
			if (!response.ok) {
				return response.json().then(errors => {
					showErrorsModal(errors);
				});
			} else {
				return response.json().then(updatedEmployee => {

					const modalElement = document.getElementById("modalEditEmpMore");
					const modalInstance = bootstrap.Modal.getInstance(modalElement);
					modalInstance.hide();

					const row = document.querySelector(`a[data-id="${id}"]`).closest("tr");
					row.children[1].textContent = updatedEmployee.name;
					row.children[2].textContent = updatedEmployee.surname;
					row.children[3].textContent = updatedEmployee.nameDepartment;
					showErrorsModal();

				});

			}

		})
		.catch(error => {
			console.error("No se ha podido enviar el formulario", error);
		});
}



function resetModal_anterior() {

	const formModal = document.getElementById("modalEditEmpMore");
	formModal.classList.remove("show", "d-block");
	formModal.style.display = "none";
	formModal.removeAttribute("aria-modal");
	formModal.removeAttribute("role");

	document.querySelectorAll(".alert-danger").forEach(alert => {
		alert.innerHTML = "";
		alert.style.display = "none";
	});

}

function resetModal() {

	document.querySelectorAll(".alert-danger").forEach(alert => {
		alert.innerHTML = "";
		alert.style.display = "none";
	});

}

document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll(".linkEditEmpMoreModal").forEach(link => {
        link.addEventListener("click", function(e) {
			e.preventDefault();
            loadDataEdit(this);
        });
    });
	 const btnSave = document.getElementById("btnSaveEmpMore");
	    if (btnSave) {
	        btnSave.addEventListener("click", function(e) {
	            reloadListEmployees(e);
	        });
	    }

	    // Botón Cerrar
	    const btnClose = document.getElementById("btnCloseEmpMore");
	    if (btnClose) {
	        btnClose.addEventListener("click", resetModal);
	    }

	    // Evento al cerrar modal
	    const modal = document.getElementById("modalEditEmpMore");
	    if (modal) {
	        modal.addEventListener("hidden.bs.modal", resetModal);
	    }
	});