# DynaTech
A set of item and machines that I made to make things easier for me and just for fun.
Most of these could possibly be overpowered or heavily underpowered.

### [Download](https://thebusybiscuit.github.io/builds/ProfElements/DynaTech/master/)
[![Build Status](https://thebusybiscuit.github.io/builds/ProfElements/DynaTech/master/badge.svg)](https://thebusybiscuit.github.io/builds/ProfElements/DynaTech/master)

## Machines
- **Auto-Kitchen** - If you have ExoticGarden installed, this machine will become available. It automatically crafts any Kitchen recipe inserted into it.
- **Growth Chambers** - Automatically grow some plants. We have multiple variants for all your needs. Supports Exotic Garden saplings, plants, and bushes.
- **Antigravity Bubble** - Temporary creative flight within a 45 block area when powered.
- **Weather Controller** - Controls the weather when given a key item (Sunflower > Clear, Lilac > Rain, Creeper Head > Thunder).
- **Potion Sprinkler** - A ranged potion applier, the potions have durability basically. Has a 10 block range.
- **Barbed Wire** - Pushes mob back in a radius. Has a 9 block range, perfect for mob farms.
- **Material Hive** - An infinite resource generator, requires Bees and a stack of the output items. Supported Materials are [here](https://github.com/ProfElements/DynaTech/blob/1b6aee96937da31c7bdb84df284392530149ce63/src/main/java/me/profelements/dynatech/items/electric/MaterialHive.java#L169). For the Material Hive, each bee you put in minuses the amount of time it takes to produce the material, so 128 bees is better then 1 bee, please note each type of bee has a different seconds minus amount. Check bee section for more info.
- **Wireless Charger** - Charges Rechargeable Items in a Players inventory in a 16 block radius around it
- **Seed Plucker** - Plucks seed from plant-based material, supports Exotic Garden Fruits, but not essences.
- **Item Band Manager** - Manages the application and ripping of Item Bands.
- **Orechid** - Changes Stone and Netherack into Overworld and Nether ores respectively
## Generators
- **Hydro Generator** - Generates energy from flowing water (Waterlog the generator)
- **Dragon Egg Generator** - Generates energy from the warmth of a dragon egg. (Place the dragon egg on top)
- **Chipping Generator** - Generates energy from damaged and or durability based items
- **Culinary Generator** - Generates energy from food energy, suports Exotic Garden Food in the Food Category.
- **Stardust Reactor** - Generates energy from Star Dust, and lots of it

## Tools
- **Picnic Basket** - Its an upgraded Cooler. It can eat any ExoticGarden custom foods, or just regular vanilla foods, it has a configurable blacklist in the items.yml
- **Electrical Stimulator** - Feed the player for energy. 
- **Inventory Filter** - Upon item pickup, if item is the same as one in the Inventory Filter's filter it voids the item.
- **Angel Gem** - Permanent Creative Flight, it has some speed settings.
- **Scoop** - The only way to get naturals Bees. Scoop them into item form.
- **Dimensional Home** - Gives you a small home in another dimension to teleport to and from. (it sends to you back to your bed spawn if you are in the dimension.)

## Bees 
- **Bee** - A Natural Bee, for each one -2 seconds to resource creation time in Material Hive.
- **Robotic Bee** - A Robotic bee made of magic and scrap parts. -2 seconds to resource creation time in Material Hive.
- **Advanced Robotic Bee** - An Advanced version of the Robotic Bee. -10 seconds to resource creation time in Material Hive.

## Item Bands 
- **Healthy Item Band** - When applied to armor or weapons gain 4 hearts while wearing or holding the item in your main hand.
- **Hasty Item Band** - When applied to armor or weapons gain 2 levels of haste while wearing or hold the item in your main hand.

## Integrations 
 - **Vex Mob Data Card** - If InfinityExpansion is installed then you get a Vex Mob Data Card to help with Ghostly Essence and Vex Gems

## Credits
 [NCBPFluffyBear](https://github.com/ncbpfluffybear) for their autocrafter code since it helped alot with the Auto-Kitchen.

 [Mooy1](https://github.com/mooy1) for their hydro generator code so I could figure out how to do waterlogging right.

 [Seggan](https://github.com/seggan) for showing me how to make a good container class instead of using Slimefun's default.

 [WalshyDev](https://github.com/WalshyDev) for answering my spam in the programming help channel.

 [Slimefun Discord](https://slimefun.dev/discord) for putting up with my outright spam of the programming help channel.

 [Slimefun](https://github.com/slimefun/slimefun4) for being incredibly intuitive to make an addon for and overall being generally helpful when needing examples.
