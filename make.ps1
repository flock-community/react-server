param (
    [parameter(Position=0)]
    [ValidateSet('s', 'f')]
    [String]$stackOrFrontend=$(throw "Full stack (s) or Frontend (f)? Example: .\make.ps1 s b"),

    [parameter(Position=1)]
    [ValidateSet('b', 'r', 'y')]
    [String]$buildOrRun=$(throw "Build (b) or Run (r)? Example: .\make.ps1 s r")
)

$currentDir = Split-Path $MyInvocation.MyCommand.Path

$buildStack    = 'docker build -f Dockerfile-full -t react-server-full .'
$buildFrontend = 'docker build -f Dockerfile-fe -t react-server-fe .'
$runStack      = 'docker run -p 8080:8080 --name stack --rm react-server-full'
$runFrontend   = 'docker run -p 4000:4000 -it --name fe --rm -v ' + $currentDir + '\src\main\frontend:/app react-server-fe bash'

if ($buildOrRun -eq 'b' -And $stackOrFrontend -eq 's') {
    Write-Output $buildStack
    Invoke-Expression $buildStack
} elseif ($buildOrRun -eq 'b' -And $stackOrFrontend -eq 'f') {
    Write-Output $buildFrontend
    Invoke-Expression $buildFrontend
} elseif ($buildOrRun -eq 'r' -And $stackOrFrontend -eq 's') {
    Write-Output $runStack
    Invoke-Expression $runStack
} elseif ($buildOrRun -eq 'r' -And $stackOrFrontend -eq 'f') {
    Write-Output $runFrontend
    Invoke-Expression $runFrontend
} else {
    Write-Output 'Yolo!'
}
