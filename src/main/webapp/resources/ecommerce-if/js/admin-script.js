document.querySelector(".aside-toggle").addEventListener("click", () => {
  if (isMobile()) {
    document.querySelector("body").classList.toggle("aside-mobile-open");
  } else {
    document.querySelector("body").classList.toggle("aside-desktop-open");
  }
});

document.querySelectorAll(".submenu").forEach((menu) =>
  menu.addEventListener("click", (event) => {
    event.preventDefault();
    menu.classList.toggle("expande");
  })
);
document
  .querySelector(".overlay-aside")
  .addEventListener("click", () =>
    document.querySelector("body").classList.toggle("aside-mobile-open")
  );
isMobile = () => {
  return (
    Math.max(document.documentElement.clientWidth, window.innerWidth || 0) < 769
  );
};
