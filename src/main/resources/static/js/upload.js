async function uploadFile() {
    let resultDiv = document.querySelector("#result");
    resultDiv.innerHTML = "";

    let errorDiv = document.querySelector("#error");
    errorDiv.innerHTML = "";

    if (!upload.files[0]) {
        errorDiv.innerHTML = "Please select file!";
        return;
    }

    await sendUploadRequest();
}

async function sendUploadRequest() {
    let formData = new FormData();
    formData.append("file", upload.files[0]);

    fetch('/matching', {
        method: "POST",
        body: formData
    }).then(response => {
        if (response.ok) {
            return response.json();
        } else {
            return response.json().then(errorData => {
                throw new Error(errorData.message);
            });
        }
    }).then(renderSuccess).catch(renderError);
}

function renderSuccess(data) {
    let resultDiv = document.querySelector("#result");
    let sumScore = 0;

    data.forEach(resultItem => {
        sumScore += resultItem.score;
        resultDiv.innerHTML += renderResultRow(resultItem);
    });

    resultDiv.innerHTML += `
        <hr>
        <div>
            Average score = <strong>${Math.round(sumScore / data.length)}%</strong>
        </div>
    `;
}

function renderResultRow(resultItem) {
    return `
        <div>
            <strong>${resultItem.left}</strong> with 
            <strong>${resultItem.right}</strong> =
            <strong>${resultItem.score}%</strong>
        </div>
    `;
}

function renderError(errorData) {
    let errorDiv = document.querySelector("#error");
    errorDiv.innerHTML = errorData.message;
}