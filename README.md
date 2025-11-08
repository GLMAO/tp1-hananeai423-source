[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/t19xNtmg)
# TP : Pattern Observer avec TimerService – Java & Maven

> **Auteur** : Hanane  
> **Date** : 08 novembre 2025  
> **Objectif** : Implémenter le pattern **Observer** avec un service de temps, gestion **thread-safe** via `PropertyChangeSupport`, et une **interface graphique Swing**.
 Spécialité: RSD
 num_dans_liste:01
---

## Objectifs du TP

| Objectif | Description |
|--------|-----------|
| 1. Créer un `TimerService` | Émet des événements toutes les **100 ms** |
| 2. Implémenter des observateurs | `Horloge`, `CompteARebours`, `HorlogeGUI` |
| 3. Utiliser `PropertyChangeSupport` | Notifications **thread-safe** |
| 4. Démontrer les bogues de concurrence | Affichage mélangé |
| 5. Résoudre avec `PropertyChangeSupport` | Synchronisation automatique |
| 6. Générer un JAR exécutable | Avec GUI incluse |

---

## Structure du Projet (Multi-module Maven)
