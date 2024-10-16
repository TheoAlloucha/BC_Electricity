import Link from "next/link";

function Navbar() {
  return (
    <div className="navbar bg-base-100">
      <div className="navbar-start">
        <div className="dropdown">
          <div tabIndex={0} role="button" className="btn btn-ghost lg:hidden">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              className="h-5 w-5"
              fill="none"
              viewBox="0 0 24 24"
              stroke="currentColor"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth="2"
                d="M4 6h16M4 12h8m-8 6h16"
              />
            </svg>
          </div>
          <ul
            tabIndex={0}
            className="menu menu-sm dropdown-content bg-base-100 rounded-box z-[1] mt-3 w-52 p-2 shadow"
          >
            <li>
              <Link href={"/"}>Accueil</Link>
            </li>
            <li>
              <Link href={"/bornes"}>Trouver une Borne</Link>
            </li>
            <li>
              <details>
                <summary>Gestion</summary>
                <ul className="bg-base-100 rounded-t-none p-2">
                  <li>
                    <Link href={"/gestion/lieux"}>Lieux</Link>
                  </li>
                  <li>
                    <Link href={"/gestion/bornes"}>Bornes</Link>
                  </li>
                </ul>
              </details>
            </li>
          </ul>
        </div>
        <a className="btn btn-ghost text-xl">Electricity BC</a>
      </div>
      <div className="navbar-center hidden lg:flex">
        <ul className="menu menu-horizontal px-1">
          <li>
            <Link href={"/"}>Accueil</Link>
          </li>
          <li>
            <Link href={"/bornes"}>Trouver une Borne</Link>
          </li>
          <li>
            <details>
              <summary>Gestion</summary>
              <ul className="bg-base-100 rounded-t-none p-2">
                <li>
                  <Link href={"/gestion/lieux"}>Lieux</Link>
                </li>
                <li>
                  <Link href={"/gestion/bornes"}>Bornes</Link>
                </li>
              </ul>
            </details>
          </li>
        </ul>
      </div>
      <div className="navbar-end">
        <a className="btn">Mon Compte</a>
      </div>
    </div>
  );
}

export default Navbar;
