This document describes notation for music.

> [!WARNING]
> This document may contain unconventional designations for the sake of compactness,
> clarity and consistency among the documents.

> [!NOTE]
> This document may apply stricter requirements on some elements of notation, than
> the regular rules of music notation.

> [!NOTE]
> It is compulsory to use local designations in contributed code.

> [!NOTE]
> Local document-scope designations (like *note* for *musical note*) are local, and
> can be overridden in other documents 

> [!NOTE]
> Notation rules are enumerated (like "rule `N-LI-ST-L-1`"). Each release 
> implements some subset of rules.

# Staff
Rule group `N-ST-*`.  
*Staff* represents single *musical line*.

## Staff line
Rule group `N-ST-SL-*`.
 - `N-ST-SL-0`. *Staff* contains five *staff lines* (enumerated
1 to 5) for indication of notes *pitch*.
 - `N-ST-SL-1`. Note on the $n$-th *staff line* indicates an offset of $2n-2$
 - *key notes* up from the note on the first *staff line*.
 - `N-ST-SL-2`. Note between the $n$-th and the $n+1$-th *staff lines* indicates
 - an offset of $2n-1$ *key notes* up. 

## Ledger
Rule group `N-ST-L-*`.  
*Ledger* is an additional short *staff line*.
 - `N-ST-L-0`. For each *note*, there should be the smallest
   possible amount of *ledgers*, so that at least one *staff line* (including added
   *ledgers*) indicates *key note*, adjacent to or equal to required one.
 - `N-ST-L-1`. *Ledgers* have length for one *note* to be positioned.
 - `N-ST-L-2`. *Ledgers* describe *pitch* as though they are regular 
*staff lines* (look `N-ST-SL-1..2`). 

## Page line
Rule group `N-ST-PL-*`.  
*Staff* is broken down to *page lines* so that it can fit into page. *Page lines* are 
enumerated from 1 (first part of *staff* is first, next part is second, and so on...). 
Set of $n$-th *page lines* for each *staff* is called *page line block*.
 - `N-ST-PL-0`. Each *page line* contains same amount of *measures*.

# Line connection symbols
Rule group `N-LCS-*`.

## Connector
Rule group `N-LCS-CON-*`.  
*Connector* is a symbol type, that connects several *staffs*.
 - `N-LCS-CON-0`. *Connector* connects 2 or more *staffs*.
 - `N-LCS-CON-1`. *Connector* can connect consecutive *staffs* only.
 - `N-LCS-CON-2`. *Connector* is displayed to the left from each set of *page lines*, 
corresponding to *staffs* being connected.

## Brace
Rule group `N-LCS-BRC-*`.  
*Brace* connects several *staffs*, played by a single player.
 - `N-LCS-BRC-0`. *Brace* implements *connector*.

## Bracket
Rule group `N-LCS-BRK-*`.  
*Bracket* connects several *staffs*.
 - `N-LCS-BRK-0`. *Bracket* implements *connector*.

# Measure symbols
Rule group `N-MS-*`.  

## Measure division symbol
Rule group `N-MS-MDS-*`.  
*Measure* is a sequence of all *valued symbols* between to neighbouring 
*measure division symbols*.
 - `N-MS-MDS-0`. Total *note value* of *valued symbols* in one *measure* is the same as
current *time signature*.
 - `N-MS-MDS-1`. *Measure division symbols* stretch on all *page line* of the same time.

### Bar line
Rule group `N-MS-BL-*`.  
*Bar line* is a *measure division symbol*, not applying any additional rules on direction
of music.
 - `N-MS-BL-0`. *Bar line* implements *measure division symbol*.

### Double bar line
Rule group `N-MS-DBL-*`.  
*Double bar line* is a *measure division symbol*, implementing ability of changing *key* or
*time* in the middle of a melody (look `N-SSS-S-2`).
 - `N-MS-DBL-0`. *Double bar line* implements *measure division symbol*.

### Bold double bar line
Rule group `N-MS-DBBL-*`.  
*Double bar line* is a *measure division symbol*, indicating the end of a melody.
 - `N-MS-DBBL-0`. *Double bold bar line* implements *measure division symbol*.
 - `N-MS-DBBL-1`. No symbols may appear after the *double bold bar line*.

### Repetition signs
Rule group `N-MS-RS-*`.  
*Repetition signs* are *measure division symbols*, used for describing repetition in music.
There are *left repetition sign* and *right repetition sign*. All *measures* between *left
repetition sign* and nearest succeeding *right repetition sign* is called *reprise*.
 - `N-MS-RS-0`. *Repetition sign* implements *measure division symbol*.
 - `N-MS-RS-1`. No *measure* may be part of two or more *reprises*. 

## Measure bracket
Rule group `N-MS-MB-*`.  
*Measure bracket* is a symbol, connecting several consecutive measures.
 - `N-MS-MB-0`. Measure bracket connects several (one ore more) measures.
 - `N-MS-MB-1`. Measures connected by bracket must be consecutive.

### Volta brace
Rule group `N-MS-VB-*`.  
*Volta brace* is a *measure bracket*, describing several ways to play *reprise end*. *Volta
brace* has a number $n$ above. Sequence of *volta braces*, covering consecutive *measures*,
is called *volta sequence*.
 - `N-MS-VB-0`. *Volta brace* implements *measure bracket*.
 - `N-MS-VB-1`. No *measure* may be part of multiple (2, or more) *volta braces*.
 - `N-MS-VB-2`. Each *volta sequence* contains at least to *volta braces*.
 - `N-MS-VB-3`. Numbers on *volta braces*, forming *volta sequence*, must be consecutive
whole numbers, starting from 1.
 - `N-MS-VB-4`. First *volta brace* in *volta sequence* is locate inside a *reprise*,
while others -- not.
 - `N-MS-VB-5`. All *volta braces* in *volta sequence* contain same amount of *measures*.
 - `N-MS-VB-6`. No change of *time* or *key* may occur.
 - `N-MS-VB-7`. Reprise, containing first *volta brace* of *volta sequence* of length $n$,
should be played $n$ times. On $i$-th iteration, measures of $i$-th *volta brace* should be 
played instead of ones in the first *volta brace*.

# Staff scoped symbols
Rule group `N-SSS-*`.  

## Clef
Rule group `N-SSS-C-*`.   
*Clef* indicates pitch of the notes, written on the staff. Each in the table below there
is a *letter name* of the *note*, located on the first *line* of the *staff*, for each *clef*.

| *clef* name | pitch | picture    |
|-------------|-------|------------|
| treble      | E4    | ***todo*** |
| alto        | F3    | ***todo*** |
| tenor       | D3    | ***todo*** |
| bass        | G2    | ***todo*** |

*treble clef* can also be modified with a number 8 or 15, placed above or below. A 
number, placed above 8 adds one *octave*, 15 adds two. For a number, placed below, same 
number of octaves should be subtracted.

 - `N-SSS-C-0`. *Clef* is written on the start of each *page line*. 

## Signature
Rule group `N-SSS-S-*`.  
*Signature* is a symbol type, representing some global information about melody, or its part.
- `N-SSS-S-0`. *Signatures* are written before the first *measure* of each *page line*.
- `N-SSS-S-1`. *Signatures* written on the same *measure* of different *staffs* must
  represent same information.
- `N-SSS-S-2`. Information, represented by *signature* of the melody may be changed by placing 
corresponding *signature* in the beginning of a same measure of each *staff* after the
*double bar line*.

### Key signature
Rule group `N-SSS-S-KS-*`.  
*Key signature* modifies the *key* of the melody by altering pitches of notes, located
on the staff. It is represented by a set of *accidentals*. For each note, unaffected by a *modifier*
*accidental*, its pitch is changed to the pitch of the note with corresponding accidental applied.
*Key signatures* for different keys are listed below: ***TODO***.
 - `N-SSS-S-KS-0`. *Key signature* implements *signature*.

### Time signature
Rule group `N-SSS-S-TS-*`.  
*Time signature* represents length of the measures of the melody. It consist of an upper number
$p$ and a lower number $q$.
 - `N-SSS-S-TS-0`. *Time signature* implements *signature*.
 - `N-SSS-S-TS-1`. $q$ should be non-negative integer power of 2.
 - `N-SSS-S-TS-2`. Total *note value* in each *measure* should be $\frac{p}q$.

# Valued symbols
Rule group `N-VS-*`.  
*Valued symbols* are symbols, that has some *value*, describing their *notation duration*. *Notation
duration* of the *valued symbol* is a time, before the next *valued symbol* of the *staff* starts.
 - `N-VS-0`. *Valued symbols*, starting at the same time, must be displayed one above another.

## Notation note
Rule group `N-VS-NN-*`.  
*Notation note* (also, for this document, *note*) is a representation of a *musical note*. 
Represented *pitch* is described by note's position on the *staff* 
(look `N-ST-SL-1..2`, `N-ST-L-2`). *Value* of the note of the *note* is described by the appearance
of the *note*. (***TODO***)
 - `N-VS-NN-0`. *Note* implements *valued symbol*.
 - `N-VS-NN-1`.  Basic *value* can be any integer power of 2 from $2^{-8}$ to $2^3$ inclusively
(and can be modified --- look `N-VSM-VM-*`).

## Chord
Rule group `N-VS-C-*`.
*Chord* is a collection of notes of the same basic *value* and different *pitch*, played at the 
same time.
 - `N-VS-C-0`. *Chord* implements *valued symbol*.
 - `N-VS-C-1`. *Chord* *note* share *note stem*.
 - `N-VS-C-2`. *Value modifiers* should be applied to all *notes* of the *chord*.
 - `N-VS-C-3`. If several *notes* of the *chord* correspond to consecutive positions on the *staff*,
odd ones (counted from the beginning of the stem) must be positioned on the wrong side of the *stem*
(to avoid collisions).

## Rest
Rule group `N-VS-R-*`.
*Rest* is a symbol, representing a pause, lasting some *note value*. *Note valued* is described
by the appearance. (***TODO***)
 - `N-VS-R-0`. *Rest* implements *valued symbol*.

# Valued symbol modifiers
Rule group `N-VSM-*`.  
*Valued symbol modifiers* may be applied on *valued symbols*, modifying their properties.

## Accidentals
Rule group `N-VSM-A-*`.  
*Accidentals* are *valued symbol modifiers*, indicating *pitch* modification.
- `N-VSM-A-0`. *Accidental* is displayed to the left of *note head*.
- `N-VSM-A-1`. To avoid collisions, *accidentals*, applied to the notes of the *chord* are being 
displayed from the end of the *stem*, with each next *accidental* being put on the rightmost 
unoccupied position (position is occupied by the *accidental* if it is less then 3 *staff lines*
apart)
- `N-VSM-A-2`. Effect of the *accidental* lasts on the uninterrupted sequence of *notes*, located at
the same height on the *staff* (or *chords*, containing such *notes*) in the same *measure*.
- `N-VSM-A-3`. Effect of the *accidental* may last on the notes of the next *measures*, if they are
*tied* to the *notes* of this *measure*, affected by this *accidental*.

### Offsetting accidentals
Rule group `N-VSM-A-OA-*`.
*Offsetting accidentals* modify *pitch* by fixed amount of *semitones*. Resulting *pitch* differs 
from the *pitch* of corresponding __natural note__ (not modified by the *key*). There is 4 types of
*offsetting accidentals*, listed in the table below:

| symbol | name         | offset | picture      |
|--------|--------------|--------|--------------|
| `#`    | sharp        | +1     | (***TODO***) |
| `##`   | double sharp | +2     | (***TODO***) |
| `b`    | flat         | -1     | (***TODO***) |
| `bb`   | double flat  | -2     | (***TODO***) |

 - `N-VSM-A-OA-0`. *Offsetting accidental* implements *accidental*.

### Naturalizing accidental
Rule group `N-VSM-A-NA-*`.
*Naturalizing accidental* (called *Natural*) removes effect of __both__ *key signature* and 
*offsetting accidentals*, giving the note its natural *pitch*.
 - `N-VSM-A-NA-0`. *Naturalizing accidental* implements *accidental*.

## Value modifiers
Rule group `N-VSM-VM-*`.  
*Value modifiers* change *valued symbol*'s *value*.
 - `N-VSM-VM-0`. *Value modifiers* must be the same among the *notes* of the *chord*.
 - 
### Dot
Rule group `N-VSM-VM-D-*`.  
Several (one or more) *dots* may be applied to the *note*. 
 - `N-VSM-VM-D-0`. *Dot* implements `value modifier`.
 - `N-VSM-VM-D-1`. $n$ *dots* multiply *basic note value* by $\frac{2^n-1}{2^{n-1}}$.

## Articulation modifiers
Rule group `N-VSM-D-*`.
*Articulation modifiers* change specify the length, volume, and style of attack of individual 
*notes*.


