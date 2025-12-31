# Pre-1.19.4 HurtCam

A Fabric mod that restores the classic pre-1.19.4 damage tilt/hurtcam behavior for Minecraft.

## What This Mod Does

In Minecraft 1.19.4 snapshot 23w06a, Mojang changed the damage tilt mechanics. The camera now tilts directionally based on where damage comes from. This mod restores the old pre-1.19.4 behavior where the camera simply rolls left/right regardless of damage direction.

**Pre-1.19.4 Behavior (This Mod):**
- Simple left/right camera roll (z-axis rotation only)
- Camera shake is consistent regardless of damage source direction
- Classic feel that many players prefer

**Post-1.19.4 Vanilla Behavior:**
- Directional damage tilt based on damage source location
- Camera can pitch, yaw, and roll in different directions

## Features

- ✅ Restores pre-1.19.4 simple left/right damage tilt
- ✅ Respects the vanilla **Damage Tilt** accessibility slider
- ✅ No configuration needed - just install and play
- ✅ Client-side only mod
- ✅ Lightweight and simple

## Installation

1. Install [Fabric Loader](https://fabricmc.net/use/)
2. Download this mod from [Modrinth](https://modrinth.com/mod/pre-1194-hurtcam) or [GitHub Releases](https://github.com/xkzuto96/Pre-1.19.4-HurtCam/releases)
3. Place the mod `.jar` file in your `.minecraft/mods` folder

## Adjusting Intensity

Use Minecraft's built-in accessibility settings to control the damage tilt intensity:

**Options → Accessibility Settings → Damage Tilt**

Adjust the slider from 0% (no tilt) to 100% (full tilt).

## Compatibility

- **Minecraft Version:** 1.21.10
- **Mod Loader:** Fabric
- **Dependencies:** None! Just Fabric Loader
- **Side:** Client-side only

## Building from Source

```bash
git clone https://github.com/xkzuto/pre-1194-hurtcam.git
cd pre-1194-hurtcam
./gradlew build
```

The built mod will be in `build/libs/`.

## License

This project is licensed under CC0-1.0 License - see the [LICENSE](LICENSE) file for details.

## Credits

**Author:** xkzuto

Inspired by the classic Minecraft damage tilt behavior that existed from version 1.3.1 to 1.19.3.

## Support

If you encounter any issues, please report them on the [GitHub Issues](https://github.com/xkzuto/pre-1194-hurtcam/issues) page.
