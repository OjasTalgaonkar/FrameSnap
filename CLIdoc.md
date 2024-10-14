# Open

open ThisGallery (opens gallery called ThisGallery)
open $5 (iterates through galleries and opens the 5th one)

command - open [galleryName/ galleryPposition]

# Rename

rename ThisGallery ThatGallery (renames gallery called ThisGallery to ThatGallery)
rename $5 ThatGallery (iterates through galleries and renames the 5th one to ThatGallery)

command - rename [galleryName / $galleryPosition] [newName]

# Import

import ThisGallery ReadmeImages/CloseWindow.png (imports image at given path into gallery ThisGallery)
import $5 ReadmeImages/CloseWindow.png (finds 5th gallery and imports image at given path into it)

command- import [galleryName / $galleryPosition] [path to your image]

# Navigation through gallery

command- next [number]

<!-- displays the image [number] images after the current one(if no number is given , it'll display the next one) -->

command- prev [number]

<!-- does the same as next , just going backward -->

# Clear

Will clear the contents of whatever gallery is requested. Will not work without galleryname for safety purposes. Can enter number position of gallery too.

command- clear [galleryName/$galleryPosition]

# Save

command- save

# Reset

command- resetmem

<!-- resets the memory of everything-->

# Exit

exits current page , if on gallery page, goes to home, if on home, shuts down

command - exit

exit command line

command- exit cmd
