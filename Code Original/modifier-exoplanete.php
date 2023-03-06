<?php
    include "connexion.php";

    $SQL_MODIFIER_EXOPLANETE = "UPDATE into exoplanete SET planete = 'Fabien', etoile = 'TOLSY' where id = 51";

    print_r($SQL_MODIFIER_EXOPLANETE);

    $requete = $basededonnees->prepare($SQL_MODIFIER_EXOPLANETE);
    $requete -> execute();

    print_r("Modification reusist !");
?>