README.md - Brainfuck VM & interpreter
=======================================


I. Liste de fichiers et répertoires
-----------------------------------

.gitignore..............\[_\]

bfck......................Script bash d'exécution du JAR brainfuck.jar

brainfuck.jar.........Dernière version fonctionnelle du projet

creation\_du\_jar..... Etapes en images expliquant comment créer l'ARchive Java à l'aide des fichiers ici présents

creation\_du\_script... Etapes en images expliquant comment créer le script de lancement à l'aide des fichiers ici présents

demos..................Démos disponibles pour tester le programme

README.md.......Ce fichier

src/brainfuck........Répertoire contenant les fichiers source du projet

src/test...........Répertoire contenant les fichiers de test du projet


II. Utilisation 
---------------

./bfck \[--option(s)\] \[-p\] \[fichier\] \[-option(s)\] \[argument(s)\]

L'ordre des options n'est pas important mais celles-cis indiquant la prise en charge d'un fichier par le programme doivent être impérativement suivis du nom du fichier en question (leur argument). Attention, le regroupement d'options n'est pas supporté (par exemple '-io arg1 arg2') !


III. DESCRIPTION
----------------

Brainfuck est un langage de programmation exotique, inventé par Urban Müller en 1993. Il tire son nom de l’union de deux mots anglais, brain (« cerveau ») et fuck (« baiser »), et joue sur les mots, puisque ce langage est volontairement simpliste, et parce que l'expression Brain Fuck évoque, en argot, ce qui met le cerveau dans un état de confusion par sa complexité apparente. Ce vocabulaire peu flatteur lui a d'ailleurs valu d'être écrit sous d'autres orthographes plus prudes, telles que Brainf*ck, Brainf*** ou encore BF.

Pour plus d'informations sur le fonctionnement du brainfuck et les instructions disponibles : https://fr.wikipedia.org/wiki/Brainfuck

Cette version de brainfuck travaille avec des données qui sont des octets non signés [0,255].

bfck est un script utilisant une archive Java (brainfuck.jar) afin de pouvoir interpréter et exécuter des programmes brainfuck écrits en instructions, en shortcuts, un mix des deux ou stocké en image (.bmp uniquement et doit aussi respecter un format particulier). Le programme accepte diverses options permettant divers services en plus de la simple exécution du fichier brainfuck.

Des commentaires peuvent être ajoutés sur une ligne en les précédant du caractère '#', l'intentation sous forme d'espaces et de tabulation est aussi supportée.

Les instructions disponibles sont :
* INCR  '+'
* DECR  '-'
* LEFT  '<'
* RIGHT '>'
* IN    ','
* OUT   '.'
* JUMP  '['
* BACK  ']'


IV. MACROS
----------

Les programmes brainfuck sous forme de texte peuvent désormais être intégrés de macros, on distingue 3 types de macros :

Macros sans paramètres :
* TO_DIGIT -> est interprétée comme 48 fois l'instruction DECR (doit être utilisée SANS autres instructions ou macros sur la même ligne, la ligne peut toutefois être indentée et posséder un commentaire)
* FROM_DIGIT -> est interprétée comme 48 fois l'instruction INCR (doit être utilisée SANS autres instructions ou macros sur la même ligne, la ligne peut toutefois être indentée et posséder un commentaire)
* TO_ALPHA -> est interprétée comme 97 fois l'instruction DECR (doit être utilisée SANS autres instructions ou macros sur la même ligne, la ligne peut toutefois être indentée et posséder un commentaire)
* FROM_ALPHA -> est interprétée comme 97 fois l'instruction INCR (doit être utilisée SANS autres instructions ou macros sur la même ligne, la ligne peut toutefois être indentée et posséder un commentaire)
* TO\_ALPHA\_CAP -> est interprétée comme 65 fois l'instruction DECR (doit être utilisée SANS autres instructions ou macros sur la même ligne, la ligne peut toutefois être indentée et posséder un commentaire)
* FROM\_ALPHA\_CAP -> est interprétée comme 65 fois l'instruction INCR (doit être utilisée SANS autres instructions ou macros sur la même ligne, la ligne peut toutefois être indentée et posséder un commentaire)

Macros avec paramètre :
* MULTI_INCR \[N\] -> est interprétée comme N fois l'instruction INCR (doit être utilisée SANS autres instructions ou macros sur la même ligne, la ligne peut toutefois être indentée et posséder un commentaire)
* MULTI_DECR \[N\] -> est interprétée comme N fois l'instruction DECR (doit être utilisée SANS autres instructions ou macros sur la même ligne, la ligne peut toutefois être indentée et posséder un commentaire)
* MULTI_LEFT \[N\] -> est interprétée comme N fois l'instruction LEFT (doit être utilisée SANS autres instructions ou macros sur la même ligne, la ligne peut toutefois être indentée et posséder un commentaire)
* MULTI_RIGHT \[N\] -> est interprétée comme N fois l'instruction RIGHT (doit être utilisée SANS autres instructions ou macros sur la même ligne, la ligne peut toutefois être indentée et posséder un commentaire)

Macros DEFINE :
* DEFINE \[P1\] \[P2\] -> à partir du moment de sa déclaration, à chaque fois que la séquence de caractères P1 sera rencontrée dans la SUITE du fichier (où qu'elle se trouve), cette séquence sera remplacée par P2. DEFINE est interprétée en priorité des autres macros, les DEFINE sont aussi exécutés dans leur ordre de déclaration. (doit être utilisée SANS autres instructions, macros et commentaires sur la même ligne)


V. FONCTIONS ET PROCEDURES
--------------------------

ATTENTION, partie non testée, suceptible de comporter de NOMBREUX BUGS, même de ne pas fonctionner.

Il est désormais possible d'utiliser des fonctions et des procédures dans le programme brainfuck. Celles-ci utilisent une mémoire vierge pour effectuer leurs instructions, il est possible de poser des initialisations dans la grille vierge à l'aide des paramètres.

La déclaration se fait AVANT l'écriture du programme principal brainfuck de cette manière, en une ligne :
@nom_fonction(param1; param2){
@ est un tag d'indication. Le nom ne doit comportes que des caractères alphanumériques et le caractère _. les paramètres sont des entiers séparés par des ;. { indique le début du corps.

Une fois dans le corps de la procédure, on ne peux pas déclarer d'autres procédures mais on peut en appeler (voir plus bas). Le corps peut aussi contenir des instructions brainfuck. Il existe un caractère spécial $ qui equivaut à un return dans les autres langages. Si ce caractère est rencontré, le contenu de la case actuellement pointée dans la mémoire de la procédure est copié dans la case actuellement pointé de la procédure appelante ou du programme principal, ce caractère doit être seul sur sa ligne et fait quitter la procédure actuelle.

Pour clore une déclaration, la ligne suivante doit être saisie :
}

Une fois dans le programme principal ou dans une procédure, on effectue l'appel de cette manière, en une seule ligne :
@nom_fonction(param1; param2)
Les paramètres saisis prennent les valeurs des cases indiquées, ces valeurs sont placées dans la novelle mémoire issue de la procédure aux cases indiquées lors de la déclaration en respectant l'ordre.


VI. OPTIONS 
-----------

--check
   
Cette option permet d'indiquer sur la sorte standard si le programme brainfuck passé à l'aide de -p est valide syntaxiquement du point de vue des instructions JUMP et BACK (ou \[ et \]), c'està dire cohérence, nombre de JUMP égal au nombre de BACK, pas de croisements de blocs, etc. Si cette option est appelée, le programme brainfuck passé en paramètre ne sera pas exécuté.

--convert

Cette option permet de traduire le code du programme brainfuck passé en paramètre en code source d'un autre langage (C ou PHP, demandé explicitement à l'utilisateur le moment venu), le code généré peut ensuite être compilé puis exécuté avec les prgrammes de votre choix. Attention, cette option ne fonctionnera pas sur un fichier comportant des procédures et/ou des fonctions.

-i	

Cette option indique que le paramètre suivant sera le fichier contenant les appels d'entrée. Lorsque le programme brainfuck exécuté aura besoin d'une entrée (IN), il lira le premier caractère du fichier passé avec ce paramètre. A chaque nouvel appel, le caractère suivant sera lu. Si le fichier contient moins de caractères que d'appels effectués, les caractères manquants seront remplacés par de caractère de code ASCII. Ce fichier doit obligatoirement exister et remplace donc l'entrée standard si spécifié.

-o	

Cette option indique que le paramètre suivant sera le fichier qui recevra les appels desortie (OUT). Lorsque le programme brainfuck exécuté fera une sortie, elle sera écrite dans le fichier à la place de la sortie standard. Si le fichier spécifié n'existe pas, il sera crée. Attention, si le fichier passé à l'aide de ce paramètre existe et que des sorties sont effectuées, son contenu sera écrasé.

-p	

Cette option OBLIGATIORE indique que le paramètre suivant sera le fichier contenant le programme brainfuck à interpréter puis à exécuter. Ce fichier doit obligatoirement exister. A la fin de l'exécution, chaque cellule dont le contenu est différent de zero est affichée de la façon suivante "cn = X". Si le programme nécessite une entrée/sortie les standardssont utilisés (un appel utilisateur explicite demandera l'entrée au bon moment).

--rewrite

Cette option permet de renvoyer sur la sorte standard le programme brainfuck passé à l'aide de -p uniquement sous forme de systaxe raccourcie (si le programme était en instruction ou en mix des deux). Si le proramme était déjà sous forme de raccourcis, le renvoie sans aucun changements. Si cette option est appelée, le programme brainfuck passé en paramètre ne sera pas exécuté.
		
--translate

Cette options permet de transformer le programme brainfuck passé en paramètre en une représentation image au format bmp de celui-ci. Le fichier de sortie est crée dans le répertoire courant et se nomme 'translated_nomFichier.bmp'.


VII. EXCEPTIONS
---------------

Arrêt du programme si une des erreures suivantes est déclanchée :
* ERREUR (1) : Une case mémoire contient une valeur en dehors de \[0;255\] (pour l'utilisateur).
* ERREUR (2) : Le pointeur de la mémoire se trouve à une valeur incorrecte (en dehors de \[0;29999\]).
* ERREUR (3) : Si le fichier d'entrée est spécifié mais impossible à ouvrir ou inexistant (option '-i').
* ERREUR (4) : Le programme brainfuck a un souci de syntaxe vis-à-vis des instructions JUMP et BACK (problème de sémantique).
* ERREUR (5) : La même option est saisie plusieurs fois au lancement du programme.
* ERREUR (6) : Une option obligatoire n'est pas saisie au lancement.
* ERREUR (7) : Une des options au lancement n'a pas le bon nombre d'argument.
* ERREUR (8) : L'image passée en paramètre (avec '-p') n'est pas interprétable.
* ERREUR (9) : L'instruction lue n'est pas une instruction brainfuck.
* ERREUR (10) : La fonction/procédure saisie n'a pas été déclarée.
* ERREUR (11) : La fonction/procédure saisie a mal été déclarée.

* ERREUR (X) : Fichier introuvable, buffer saturé, etc.

* AVERTISEMENT si : Un argument est mal placé (lié à aucune option), mais pas d'arrêt du programme.


VIII. DIVERS
----------

Ce programme est un projet réalisé dans le cadre du SI3 de Polytech Nice Sophia-Antipolis, année 2016 par l'équipe Triplot, composée de Yijie Wang, Mohd Najib et Dylan Ritrovato. Il y a une possibilité que des bugs non détectés soient présents, si tel est le cas, vous pouvez les signaler par mail à dylan.xanto@gmail.com. Merci de votre compréhension.
