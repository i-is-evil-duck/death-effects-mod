# Death Effects

A Minecraft Forge mod that adds customizable cosmetic death effects. When a player dies, a visual effect plays at their death location — choose from lightning strikes, explosions, fire bursts, soul escapes, and smoke clouds.

## Effects

| Effect | Description |
|--------|-------------|
| **Lightning Strike** | Electric sparks shoot upward with a thunderclap and flash |
| **Explosion** | Explosion particles with smoke rings and blast sounds |
| **Fire Burst** | Ring of flames with lava particles and fire sounds |
| **Soul Escape** | Soul particles rising with soul fire and wither sounds |
| **Smoke Cloud** | Campfire smoke and ash cloud with crackling sounds |

## Features

- **5 unique death effects** with particles and sounds
- **Random or fixed mode** — pick a specific effect or random from enabled pool
- **Per-effect toggles** — enable/disable individual effects
- **Creative mode toggle** — optionally skip effects in creative
- **Server config** — all settings in `deatheffects.toml`
- **Test command** — `/deatheffect <type>` to preview effects in-game
- **Purely cosmetic** — no damage, no item loss, no gameplay changes

## Requirements

- Minecraft 1.20.1
- Forge 47.2.0+
- Java 17

## Building

```bash
gradle build
```

The built JAR will be in `build/libs/deatheffects-1.0.0.jar`.

## Installation

1. Install Minecraft Forge for 1.20.1
2. Place the built JAR in your `.minecraft/mods/` folder
3. Launch Minecraft

## Configuration

Server-side config file `deatheffects.toml`:

| Setting | Default | Description |
|---------|---------|-------------|
| enableEffects | true | Master toggle for all death effects |
| effectMode | 0 | 0=Random, 1-5=Specific effect |
| enableLightning | true | Enable Lightning Strike in random pool |
| enableExplosion | true | Enable Explosion in random pool |
| enableFireBurst | true | Enable Fire Burst in random pool |
| enableSoulEscape | true | Enable Soul Escape in random pool |
| enableSmokeCloud | true | Enable Smoke Cloud in random pool |
| enableInCreative | false | Play effects for creative mode deaths |
| effectRadius | 4.0 | Chunk radius for effect visibility |

## Commands

| Command | Description | Permission |
|---------|-------------|------------|
| `/deatheffect <0-4>` | Play a specific effect at your location | OP (level 2) |
| `/deatheffect list` | List all available effect types | All players |

## License

All Rights Reserved. Copyright (c) i-is-evil-duck.
