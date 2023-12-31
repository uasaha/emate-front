const searchInput = document.getElementById("search-input");

function search() {
  const inputs = document.getElementById("search-input");
  const keyword = inputs.value;

  if (keyword === null || keyword === "") {
    alert("검색어를 입력해주세요.");
    window.location.reload();
  } else {
    window.location.href = "/contents/search?key=" + keyword;
  }
}

searchInput.addEventListener("keydown", function withEnter() {
  if (event.keyCode === 13) {
    // event.preventDefault();
    const searchButton = document.getElementById("search-button");
    searchButton.click();
  }
});