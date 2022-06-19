# Bridging

![Version: 1.1](https://img.shields.io/badge/Version-1.1-blue?style=for-the-badge)
![Java: 16](https://img.shields.io/badge/Java-17-red?style=for-the-badge)
![Fabric: 1.18.2](https://img.shields.io/badge/Fabric-1.18.2-orange?style=for-the-badge)

> *This branch is a backport of 1.19's features for compatability. Do not expect support.*

A fabric port of Quark's Reach-Around Placement feature with some tweaks to refine its experience. It's similar to Minecraft:
Bedrock Edition's bridging features. Blocks can be placed horizontally for adjacent block gaps or vertically underneath
blocks where the interactable surface is not visible.


Changes from Quark include:

- Coloured crosshair icons when a block can be placed using the modified placement
    - Located at `assets/placementpog/textures/gui/placement_icons.png`
- More forgiving placement logic
    - Can place when under 2 block ceilings
    - Target areas are larger

