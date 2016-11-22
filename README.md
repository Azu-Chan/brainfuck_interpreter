README.md - Brainfuck VM & interpreter
=======================================


I. Liste de fichiers et répertoires
-----------------------------------

.gitignore..............\[_\]

bfck......................Script bash d'exécution du JAR brainfuck.jar

brainfuck.jar.........Dernière version fonctionnelle du projet

demos..................Démos disponibles pour tester le programme

README.md.......Ce fichier

src/brainfuck........Répertoire contenant les fichiers source du projet


II. Utilisation 
---------------

./bfck \[--option(s)\] \[-p\] \[fichier\] \[-option(s)\] \[argument(s)\]

L'ordre des options n'est pas important mais celles-cis indiquant la prise en charge d'un fichier par le programme doivent être impérativement suivis du nom du fichier en question (leur argument). Attention, le regroupement d'options n'est pas supporté (par exemple '-io arg1 arg2') !


III. DESCRIPTION
----------------

Brainfuck est un langage de programmation exotique, inventé par Urban Müller en 1993. Il tire son nom de l’union de deux mots anglais, brain (« cerveau ») et fuck (« baiser »), et joue sur les mots, puisque ce langage est volontairement simpliste, et parce que l'expression Brain Fuck évoque, en argot, ce qui met le cerveau dans un état de confusion par sa complexité apparente. Ce vocabulaire peu flatteur lui a d'ailleurs valu d'être écrit sous d'autres orthographes plus prudes, telles que Brainf*ck, Brainf*** ou encore BF.

Pour plus d'informations sur le fonctionnement du brainfuck et les instructions disponibles : https://fr.wikipedia.org/wiki/Brainfuck
Cette version de brainfuck travaille avec des données qui sont des octés non signés [0,255].

bfck est un script utilisant une archive Java (brainfuck.jar) afin de pouvoir interpréter et exécuter des programmes brainfuck écrits en instructions, en shortcuts, un mix des deux ou stocké en image (.bmp uniquement et doit aussi respecter un format particulier). Le programme accepte diverses options permettant divers services en plus de la simple exécution du fichier brainfuck.
Des commentaires peuvent être ajoutés sur une ligne en les précédant du caractère '#', l'intentation sous forme d'espaces et de tabulation est aussi supportée.


IV. OPTIONS 
-----------

--check
   
Cette option permet d'indiquer sur la sorte standard si le programme brainfuck passé à l'aide de -p est valide syntaxiquement du point de vue des instructions JUMP et BACK (ou [ et ]), c'està dire cohérence, nombre de JUMP égal au nombre de BACK, pas de croisements de blocs, etc. Si cette option est appelée, le programme brainfuck passé en paramètre ne sera pas exécuté.

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


V. EXCEPTIONS
-------------

Arrêt du programme si une des erreures suivantes est déclanchée :
* ERREUR (1) : Une case mémoire contient une valeur en dehors de \[0,255\] (pour l'utilisateur).
* ERREUR (2) : Le pointeur de la mémoire se trouve à une valeur incorrecte (en dehors de [0;29999]).
* ERREUR (3) : Si le fichier d'entrée est spécifié mais impossible à ouvrir ou inexistant (option -i).
* ERREUR (4) : Le programme brainfuck a un souci de syntaxe vis-à-vis des instructions JUMP et BACK (problème de sémantique).
* ERREUR (5) : La même option est saisie plusieurs fois au lancement du programme.
* ERREUR (6) : Une option obligatoire n'est pas saisie au lancement.
* ERREUR (7) : Une des options au lancement n'a pas le bon nombre d'argument.
* ERREUR (8) : L'image passée en paramètre (avec '-p') n'est pas interprétable.
* ERREUR (9) : L'instruction lue n'est pas une instruction brainfuck.

* ERREUR (X) : Fichier introuvable, buffer saturé, etc.

* AVERTISEMENT si : Un argument est mal placé (lié à aucune option), mais pas d'arrêt du programme.


VI. DIVERS
----------

Ce programme est un projet réalisé dans le cadre du SI3 de Polytech Nice Sophia-Antipolis, année 2016 par l'équipe Triplot, composée de Yijie Wang, Mohd Nijab et Dylan Ritrovato. Il y a une possibilité que des bugs non détectés soient présents, si tel est le cas, vous pouvez les signaler par mail à dylan.xanto@gmail.com. Merci de votre compréhension.
