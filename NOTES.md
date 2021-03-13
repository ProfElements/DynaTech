## New Additions
- **Tunnel Bores** : An automated mining minecart using coal and rails. //Idea > https://wiki.gm4.co/wiki/Tunnel_Bores
- **Dog House** : When right clicked on a dog changes it into a `Companion` that can level up and help you out. //Texture > d089b260aedd4bf2cf0f9ac7fb0e9e3a461cc4fef098ecd660fd0c9827986f5c
- **Advanced Enchantment Table** : Taking much more exp you can choose the enchantments on items
- **Stave** : A magical staff that changes based on what runes you add to it
- **Skeletal Bee** : A Bee that sometimes makes material hive output double at the cost of dying // Texture > 7129324c900a58cf34b42ce3f07c1753f9f7523f22bccbc9afc63f191fd1395
- **Tesseract** : A 2 way teleporting of items and energy
- **Wireless Energy Hotspot && Wireless Energy Bank** : Same idea as the Tesseract except just with energy and some linking.

## Steam Revolution
- **Steam Tank** : A tank that hold steams for all machines in a Steam network can grab and use
- **Boiler** : Takes in water and coal and produces steam.
- **Furnace Heater** : When placed adjacent to a furnace powers it using steam instead of conventional materials
- **Steam Workbench** : Auto crafter using steam 
- **Steam Turbine** : Takes in steam and produces electricity
- **Ore Washer** : Cleans ores and other materials to produce more pure variants (2x ore doubling + chance at nuggets)
- **Simple Steam Pipe** : Connects steam blocks together 


## Out of Depth
- **Talisman Pouch** : Put talisman in pouch and works like inv or enderchest //Require visibility changes to slimefun for little gain.
- **Training Dummy** : Unkillable Armor Stand that show how much damage you deal //Dont know how to use EntityInteractEvent
- **Treasure Chest** : A one time lootbox from any lootchest //Just dont know how to hack the inventory stuffs

## Questioning
- **Basalt Generator** : One block basalt generator //I really dont know how useful this would be 
- **Blackstone Generator**: One block blackstone generator //I really dont knoww how useful this would be
- **Bossbar Projector**: Projects a bossbar with a name and bossbar type in a range //I dont know how hard implmenting this would be

## Coding and commit conventions
- **fix** : bugfix or general fix to item or system //GitMoji emoji : :bug:
- **feat** : adding a new feature to an item or a new item //GitMoji emoji : :sparkles:
- **BREAKING CHANGE** : litterally what it says on the tin, if this is here expect breakage //GitMoji emoji : :recycle: 
- **wip** : Work in Progress stuff to eventually become a feature or bugfix //GitMoji emoji : :construction:
- **work** : general working on weather its cleaninp up or otherwise //GitMoji emoji : :construction:

## MISC Notes
To register a recipe without a SlimefunItem + SlimefunItemStack combo 
use RecipeType.register(ItemStack[], ItemStack) 
//recipe, result ex. RecipeType.ENHANCED_CRAFING_TABLE.register(new ItemStack[] {new ItemStack(Material.OAK)}, new ItemStack(Material.SPRUCE));
