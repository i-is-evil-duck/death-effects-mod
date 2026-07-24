# Death Effects

Adds customizable cosmetic death effects to Minecraft. When a player dies, a visual effect plays at their death location.

## Effects

- **Lightning Strike** — Electric sparks shoot upward with a thunderclap and flash
- **Explosion** — Explosion particles with smoke rings and blast sounds
- **Fire Burst** — Ring of flames with lava particles and fire sounds
- **Soul Escape** — Soul particles rising with soul fire and wither sounds
- **Smoke Cloud** — Campfire smoke and ash cloud with crackling sounds

## Features

- 5 unique death effects with particles and sounds
- Random or fixed mode — pick a specific effect or random from enabled pool
- Per-effect toggles — enable/disable individual effects
- Creative mode toggle — optionally skip effects in creative
- Server config — all settings in `deatheffects.toml`
- Purely cosmetic — no damage, no item loss, no gameplay changes

## Commands

| Command | Description | Permission |
|---------|-------------|------------|
| `/deatheffect set random\|lightning\|explosion\|fire\|souls\|smoke` | Set server death effect | OP |
| `/deatheffect preview <type>` | Preview effect at your location | All |
| `/deatheffect current` | Show current setting | All |
| `/deatheffect list` | List all effects with on/off status | All |

## Config

```toml
[tacz0ing]
  enableEffects=true
  effectMode=1
  enableLightning=true
  enableExplosion=true
  enableFireBurst=true
  enableSoulEscape=true
  enableSmokeCloud=true
  enableInCreative=false
  effectRadius=4.0
```

## Links

- [Source Code](https://github.com/i-is-evil-duck/death-effects-mod)
- [Issue Tracker](https://github.com/i-is-evil-duck/death-effects-mod/issues)
