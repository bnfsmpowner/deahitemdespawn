![Minecraft](https://img.shields.io/badge/Minecraft-1.21+-brightgreen)
![Java](https://img.shields.io/badge/Java-21-orange)
![Platform](https://img.shields.io/badge/Platform-Paper%20%7C%20Purpur-blue)
![License](https://img.shields.io/badge/license-MIT-blue)
![Status](https://img.shields.io/badge/status-stable-brightgreen)

# DeathItemDespawn

A lightweight Minecraft plugin that extends the lifetime of items dropped on player death, preventing them from despawning after the default 5 minutes.

---

## ✨ Features

* 🪦 Death items stay for a configurable time (default: 30 minutes)
* Accurate tracking using ItemSpawnEvent
* Works with water, explosions, and item movement
* Prevents vanilla 5-minute despawn
* Only affects player death drops
* Lightweight and efficient

---

## 📦 Requirements

* Paper / Purpur server (1.21+)

---

## 📦 Installation

1. Download the plugin `.jar`
2. Place it in your `/plugins` folder
3. Restart your server

---

## 🚀 Usage

No commands required — the plugin works automatically when a player dies.

---

## ⚙️ Configuration

```yaml
despawn-time-minutes: 30
```

---

## ⚙️ How it works

The plugin captures death drops:

```java
@EventHandler
public void onPlayerDeath(PlayerDeathEvent event)
```

Then listens for item spawn events:

```java
@EventHandler
public void onItemSpawn(ItemSpawnEvent event)
```

Matching items are:
- Marked as death items
- Given unlimited lifetime
- Removed after the configured time

---

## 📄 Command

| Command | Description |
|--------|------------|
| None   | Plugin is automatic |

---

## 📌 Notes

* Only affects items dropped from player deaths
* Does NOT affect normal dropped items or mob drops

---

## 📄 License

This project is licensed under the MIT License.
