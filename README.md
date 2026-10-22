# 🥖 Démo DevOps (Java) : la Boulangerie du Groupe 2

**Objectif :** montrer en direct un pipeline CI/CD. À chaque modification, le code Java est **testé automatiquement avec JUnit**, puis le site est **généré et mis en production automatiquement** si tout est vert. Si un test échoue, la mise en production est **bloquée** : le bug n'arrive jamais chez l'utilisateur.

Mini-conférence « DevOps, Platform Engineering & SRE », Gestion de projet Agile, M2 Logiciel, Université Gustave Eiffel.

---

## Ce que contient le projet

| Fichier | Rôle dans la démo |
|---|---|
| `src/main/java/.../Panier.java` | Le calcul du prix du panier : le code du **Dev** |
| `src/test/java/.../PanierTest.java` | Les **tests automatiques** JUnit 5 |
| `src/main/java/.../GenerateurSite.java` | Génère le site (la **prod**) : tous les prix affichés sont calculés par `Panier` |
| `.github/workflows/pipeline.yml` | Le **pipeline** : 1) tests JUnit → 2) génération du site + mise en production (seulement si les tests passent) |

En local (Java 21 + Maven) :
```bash
mvn test                                                                        # lancer les tests
mvn compile && java -cp target/classes fr.univeiffel.boulangerie.GenerateurSite site   # générer le site dans site/
```

---

## Mise en place (une seule fois, environ 5 min)

1. Sur GitHub, crée un dépôt **public** nommé `demo-devops`, sans README.
2. Dans ce dossier :
   ```bash
   git init
   git add .
   git commit -m "Version 1 de la boulangerie"
   git branch -M main
   git remote add origin https://github.com/foued21/demo-devops.git
   git push -u origin main
   ```
3. Sur GitHub : **Settings → Pages → Build and deployment → Source : « GitHub Actions »**.
4. Onglet **Actions** → relance « Pipeline CI/CD » (bouton **Run workflow**) si le premier passage a échoué parce que Pages n'était pas encore activé.
5. Le site est en ligne sur **https://foued21.github.io/demo-devops/**

---

## Déroulé de la démo (environ 3 min 30)

**Avant de commencer :** ouvrir 3 onglets.
1. Le site en ligne
2. Le dépôt sur GitHub
3. L'onglet **Actions**

### Scénario 1 : une modification qui passe ✅
1. Montrer le site, et en bas la version et l'heure de mise en ligne.
2. Sur GitHub, ouvrir `GenerateurSite.java` → crayon ✏️ → changer la constante `ANNONCE` :
   `"🥐 Nouveau : les croissants arrivent bientôt !"` → `"🥐 Les croissants sont là !"`
3. **Commit changes** directement sur `main`.
4. Onglet **Actions** : montrer « 1 · Tests automatiques (JUnit) » ✅, puis « 2 · Mise en production » ✅.
5. Rafraîchir le site : l'annonce a changé, et la version et l'heure aussi.

### Scénario 2 : un bug qui est bloqué ❌
1. Ouvrir `Panier.java` → crayon ✏️ → dans `total()`, remplacer `quantite * prixCentimes` par `quantite + prixCentimes`.
2. **Commit changes**.
3. Onglet **Actions** : « 1 · Tests automatiques » ❌ en rouge (3 tests JUnit sur 5 échouent), et « 2 · Mise en production » **ne se lance pas**.
4. Rafraîchir le site : il affiche toujours la bonne version, et 3 baguettes = 3,60 €.
5. **Remettre `*`** et commiter pour revenir au vert (ou le faire juste après la présentation).

---

## Plan B
- Enregistrer une **vidéo de secours** des deux scénarios la veille.
- Le pipeline Java prend environ 1 min à 1 min 30 (installation de Java + Maven) : prévoir quoi dire pendant qu'il tourne.
